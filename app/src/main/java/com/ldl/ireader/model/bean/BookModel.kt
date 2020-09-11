package com.ldl.ireader.model.bean

/**
 * 作者：LDL 创建时间：2020/9/8
 * 类说明：
 */
data class BookModel(
    var author: String,
    var bookId: Int,
    var categoryName: String,
    var chapterStatus: String,
    var coverImg: String,
    var desc: String,
    var title: String,
    var word: String,
)