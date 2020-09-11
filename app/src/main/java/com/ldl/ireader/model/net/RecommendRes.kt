package com.ldl.ireader.model.net

import com.ldl.ireader.model.bean.BookModel

/**
 * 作者：LDL 创建时间：2020/9/11
 * 类说明：
 */
data class RecommendRes(
    var list: List<BookModel>,
    var pageNum: Int,
    var pageSize: Int,
    var total: Int,
)