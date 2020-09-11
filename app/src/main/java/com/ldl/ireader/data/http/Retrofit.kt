package com.ldl.ireader.data.http

import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.Utils
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.ldl.ireader.BuildConfig
import com.ldl.ireader.constant.Constant
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * 作者：LDL 创建时间：2020/9/8
 * 类说明：
 */

private const val TIME_OUT = 60L

val httpLoggingInterceptor = HttpLoggingInterceptor().also {
    it.level = HttpLoggingInterceptor.Level.BODY
}

private fun provideClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        builder.addInterceptor(httpLoggingInterceptor)
    }
    val cacheFile = File(Constant.PATH_CACHE)
    val cache = Cache(cacheFile, 1024 * 1024 * 50)
    val cacheInterceptor = Interceptor { chain ->
        var request = chain.request()
        if (!NetworkUtils.isConnected()) {
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }
        val response = chain.proceed(request)
        if (NetworkUtils.isConnected()) {
            val maxAge = 0
            // 有网络时, 不缓存, 最大保存时长为0
            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        } else {
            // 无网络时，设置超时为4周
            val maxStale = 60 * 60 * 24 * 28
            response.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        response
    }
    //设置缓存
    builder.addNetworkInterceptor(cacheInterceptor)
    builder.addInterceptor(cacheInterceptor)
    builder.cache(cache)
    //设置超时
    builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
    builder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
    builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS)
    //错误重连
    builder.retryOnConnectionFailure(true)
    //cookie认证
    builder.cookieJar(
        PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(Utils.getApp())
        )
    )
    return builder.build()
}

private fun createRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(Apis.HOST)
    .client(provideClient())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object ApiService : Apis by createRetrofit().create(Apis::class.java)