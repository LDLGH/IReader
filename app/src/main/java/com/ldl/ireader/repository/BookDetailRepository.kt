package com.ldl.ireader.repository

import com.ldl.ireader.base.BaseRepository
import com.ldl.ireader.data.http.Apis

/**
 * 作者：LDL 创建时间：2020/9/11
 * 类说明：
 */
class BookDetailRepository(private val mApis: Apis) : BaseRepository() {

    suspend fun getDetail(bookId: Int) = mApis.getDetail(bookId)

    suspend fun getRecommend(pageNum: Int, bookId: Int) = mApis.getRecommend(pageNum, bookId)
}