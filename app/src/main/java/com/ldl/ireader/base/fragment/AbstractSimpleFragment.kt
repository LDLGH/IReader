package com.ldl.ireader.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * 作者：LDL 创建时间：2020/9/9
 * 类说明：
 */
abstract class AbstractSimpleFragment : Fragment() {

    protected var mRootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initToolbar()
        initData()
    }

    protected abstract fun getLayoutId(): Int

    protected open fun initView() {}

    /**
     * 初始化ToolBar
     */
    protected open fun initToolbar() {}

    protected open fun initData() {}
}