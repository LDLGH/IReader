package com.ldl.ireader.ui.activity

import com.blankj.utilcode.util.BarUtils
import com.google.android.material.appbar.AppBarLayout
import com.ldl.ireader.R.layout.activity_book_detail
import com.ldl.ireader.base.activity.BaseActivity
import com.ldl.ireader.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.activity_book_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

/**
 * 作者：LDL 创建时间：2020/9/11
 * 类说明：书籍详情
 */
class BookDetailActivity : BaseActivity() {

    private val mViewModel by viewModel<BookDetailViewModel>()

    override fun getLayoutId(): Int = activity_book_detail

    override fun initView() {
        BarUtils.transparentStatusBar(this)
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val total = appBarLayout.totalScrollRange * 1.0f
            //计算出滑动百分比
            val p = abs(verticalOffset) / total
            if (p > 0.5) {
                tv_title.alpha = 1.0f / 0.5f * (p - 0.5f)
            } else {
                tv_title.alpha = 0f
            }
        })
    }

}