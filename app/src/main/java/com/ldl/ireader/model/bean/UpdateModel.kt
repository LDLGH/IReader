package com.ldl.ireader.model.bean

/**
 * 作者：LDL 创建时间：2020/9/11
 * 类说明：
 */
data class UpdateModel(
    var chapterId: Long,
    var chapterName: String,
    var chapterStatus: String,
    var time: Long,
)