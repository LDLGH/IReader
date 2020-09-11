package com.ldl.ireader.ui.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.ireader.R
import com.ldl.ireader.model.bean.BookModel
import com.ldl.ireader.model.bean.DiscoveryModel
import com.ldl.ireader.ui.activity.BookDetailActivity
import com.ldl.ireader.widget.GridSpaceItemDecoration

/**
 * 作者：LDL 创建时间：2020/9/10
 * 类说明：
 */
class DiscoveryAdapter(list: MutableList<DiscoveryModel>) :
    BaseQuickAdapter<DiscoveryModel, BaseViewHolder>(R.layout.item_discovery, list),
    LoadMoreModule {

    init {
        addChildClickViewIds(R.id.tv_seeMore)
    }

    override fun convert(holder: BaseViewHolder, item: DiscoveryModel) {
        holder.setText(R.id.tv_categoryName, item.categoryName)
        val rvBooks = holder.getView<RecyclerView>(R.id.rv_books)
        if (rvBooks.itemDecorationCount < 1) {
            rvBooks.addItemDecoration(GridSpaceItemDecoration(3,
                ConvertUtils.dp2px(10f),
                ConvertUtils.dp2px(10f)))
            rvBooks.setHasFixedSize(true)
            rvBooks.isNestedScrollingEnabled = false
            rvBooks.layoutManager = GridLayoutManager(rvBooks.context, 3)
        }
        var list = item.bookList
        if (item.bookList.size > 6) {
            list = item.bookList.subList(0, 6)
        }
        val booksAdapter = BooksAdapter(list as MutableList<BookModel>)
        rvBooks.adapter = booksAdapter
        booksAdapter.setOnItemClickListener { adapter, view, position ->
            ActivityUtils.startActivity(BookDetailActivity::class.java)
        }
    }
}