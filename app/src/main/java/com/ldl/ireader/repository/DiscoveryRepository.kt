package com.ldl.ireader.repository

import com.ldl.ireader.base.BaseRepository
import com.ldl.ireader.data.http.Apis

/**
 * 作者：LDL 创建时间：2020/9/8
 * 类说明：
 */
class DiscoveryRepository(private val mApis: Apis) : BaseRepository() {

    suspend fun getDiscovery(pageNum: Int) = mApis.getDiscovery(pageNum)
}