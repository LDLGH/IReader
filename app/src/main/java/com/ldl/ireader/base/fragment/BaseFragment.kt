package com.ldl.ireader.base.fragment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.ldl.ireader.base.view.AbstractView

/**
 * 作者：LDL 创建时间：2020/9/9
 * 类说明：
 */
abstract class BaseFragment : AbstractSimpleFragment(), AbstractView {

    protected lateinit var mActivity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun showNormal() {

    }

    override fun showError() {
    }

    override fun showLoading() {
    }

    override fun reload() {
    }

    override fun showLoadingDialog() {
    }

    override fun hideLoadingDialog() {
    }

    override fun showToast(message: String?) = ToastUtils.showShort(message)

    override fun showSnackBar(message: String?) {

    }

}