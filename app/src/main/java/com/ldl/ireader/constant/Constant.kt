package com.ldl.ireader.constant

import com.blankj.utilcode.util.Utils
import java.io.File

/**
 * 作者：LDL 创建时间：2020/9/8
 * 类说明：
 */
class Constant {

    companion object {
        private val PATH_DATA =
            Utils.getApp().cacheDir.absolutePath.toString() + File.separator + "data"

        val PATH_CACHE = "$PATH_DATA/NetCache"

        const val PAGE_SIZE = 10

        const val HTTP_SUCCESS = 0
    }
}