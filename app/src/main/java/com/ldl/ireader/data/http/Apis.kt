package com.ldl.ireader.data.http

import com.ldl.ireader.base.BaseResponse
import com.ldl.ireader.constant.Constant
import com.ldl.ireader.model.net.BookDetailRes
import com.ldl.ireader.model.net.DiscoveryRes
import com.ldl.ireader.model.net.RecommendRes
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 作者：LDL 创建时间：2020/9/8
 * 类说明：
 */
interface Apis {

    companion object {
        const val HOST = "http://yuenov.com:15555/"
    }

    /**
     * App内发现页面接口
     * @param pageNum 请求第几页的数据，pageNum最小值为1
     * @param pageSize 请求每页多少条的数据
     * */
    @GET("app/open/api/category/discovery")
    suspend fun getDiscovery(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int = Constant.PAGE_SIZE,
    ): BaseResponse<DiscoveryRes>

    /**
     * 书籍详情
     * @param bookId 书籍号
     * */
    @GET("app/open/api/book/getDetail")
    suspend fun getDetail(
        @Query("bookId") bookId: Int,
    ): BaseResponse<BookDetailRes>

    /**
     * 书籍推荐
     * @param pageNum 请求第几页的数据，pageNum最小值为1
     * @param pageSize 请求每页多少条的数据
     * @param bookId 书籍号
     * */
    @GET("app/open/api/book/getRecommend")
    suspend fun getRecommend(
        @Query("bookId") bookId: Int,
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int = Constant.PAGE_SIZE,
    ): BaseResponse<RecommendRes>
}