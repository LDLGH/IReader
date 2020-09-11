package com.ldl.ireader.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.ireader.R
import com.ldl.ireader.model.bean.BookModel
import com.ldl.ireader.util.loader.ImageLoader

/**
 * 作者：LDL 创建时间：2020/9/10
 * 类说明：
 */
class BooksAdapter(list: MutableList<BookModel>) :
    BaseQuickAdapter<BookModel, BaseViewHolder>(R.layout.item_books, list) {

    override fun convert(holder: BaseViewHolder, item: BookModel) {
        val imageView = holder.getView<ImageView>(R.id.iv_coverImg)
        ImageLoader.loadImageRound(imageView, item.coverImg, 5)
        holder.setText(R.id.tv_title, item.title)
    }
}