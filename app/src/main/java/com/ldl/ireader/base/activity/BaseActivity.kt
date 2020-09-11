package com.ldl.ireader.base.activity

import com.blankj.utilcode.util.SnackbarUtils
import com.blankj.utilcode.util.ToastUtils
import com.ldl.ireader.base.view.AbstractView

/**
 * 作者：LDL 创建时间：2020/9/9
 * 类说明：
 */
abstract class BaseActivity : AbstractSimpleActivity(), AbstractView {

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
        SnackbarUtils.with(window.decorView)
            .setMessage(message!!)
            .showSuccess()
    }

}