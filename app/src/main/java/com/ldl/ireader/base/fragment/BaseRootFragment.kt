package com.ldl.ireader.base.fragment

import android.view.View
import com.billy.android.loading.Gloading

/**
 * 作者：LDL 创建时间：2020/9/9
 * 类说明：
 */
abstract class BaseRootFragment : BaseFragment() {
    private var mHolder: Gloading.Holder? = null

    protected fun initLoadingStatusView(view: View) {
        if (mHolder == null) {
            mHolder = Gloading.getDefault().wrap(view).withRetry {
                reload()
            }
        }
    }

    override fun showNormal() {
        mHolder?.showLoadSuccess()
    }

    override fun showError() {
        mHolder?.showLoadFailed()
    }

    override fun showLoading() {
        mHolder?.showLoading()
    }
}