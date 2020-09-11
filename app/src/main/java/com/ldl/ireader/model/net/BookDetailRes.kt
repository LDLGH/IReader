package com.ldl.ireader.model.net

import com.ldl.ireader.model.bean.BookModel
import com.ldl.ireader.model.bean.UpdateModel

/**
 * 作者：LDL 创建时间：2020/9/11
 * 类说明：
 */
data class BookDetailRes(
    var author: String,
    var bookId: Int,
    var categoryName: String,
    var chapterNum: String,
    var coverImg: String,
    var desc: String,
    var title: String,
    var update: UpdateModel,
    var word: String,
    var recommend: List<BookModel>,
)