package com.ldl.ireader.ui.activity

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.FragmentUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ldl.ireader.R
import com.ldl.ireader.R.layout.activity_main
import com.ldl.ireader.base.activity.BaseActivity
import com.ldl.ireader.ui.fragment.BookmarkFragment
import com.ldl.ireader.ui.fragment.BookshelfFragment
import com.ldl.ireader.ui.fragment.DiscoveryFragment
import com.ldl.ireader.ui.fragment.MyFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 作者：LDL 创建时间：2020/9/9
 * 类说明：
 */
class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val mFragments by lazy {
        arrayListOf<Fragment>()
    }

    override fun getLayoutId(): Int = activity_main

    override fun initView() {
        initFragments()
        navigation.selectedItemId = R.id.navigation
        navigation.setOnNavigationItemSelectedListener(this)
    }

    private fun initFragments() {
        mFragments.add(BookshelfFragment())
        mFragments.add(DiscoveryFragment())
        mFragments.add(BookmarkFragment())
        mFragments.add(MyFragment())
        FragmentUtils.add(supportFragmentManager, mFragments, R.id.fl_content, 0)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_bookshelf -> {
                FragmentUtils.showHide(0, mFragments)
            }
            R.id.action_discovery -> {
                FragmentUtils.showHide(1, mFragments)
            }
            R.id.action_bookmark -> {
                FragmentUtils.showHide(2, mFragments)
            }
            R.id.action_my -> {
                FragmentUtils.showHide(3, mFragments)
            }
            else -> {
            }
        }
        return true
    }
}