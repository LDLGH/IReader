package com.ldl.ireader.app

import android.app.Application
import com.billy.android.loading.Gloading
import com.blankj.utilcode.util.Utils
import com.ldl.ireader.widget.GlobalAdapter
import di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * 作者：LDL 创建时间：2020/9/3
 * 类说明：
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        initKoin()
        initGloading()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule)
        }
    }

    private fun initGloading() {
        Gloading.initDefault(GlobalAdapter())
    }
}