package com.ldl.ireader.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * 作者：LDL 创建时间：2020/9/9
 * 类说明：
 */
abstract class AbstractSimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initToolbar()
        initData()
    }

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    /**
     * 在initData之前执行
     */
    protected open fun initView() {}

    /**
     * 初始化ToolBar
     */
    protected open fun initToolbar() {}

    /**
     * 初始化数据
     */
    protected open fun initData() {}
}