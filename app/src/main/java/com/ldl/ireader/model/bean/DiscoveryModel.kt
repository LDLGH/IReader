package com.ldl.ireader.model.bean

/**
 * 作者：LDL 创建时间：2020/9/8
 * 类说明：
 */
data class DiscoveryModel(
    var bookList: List<BookModel>,
    var categoryName: String,
    var type: String,
    var categoryId: Int
)