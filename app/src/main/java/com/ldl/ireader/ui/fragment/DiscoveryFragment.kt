package com.ldl.ireader.ui.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.StringUtils
import com.ldl.ireader.R
import com.ldl.ireader.R.layout.fragment_discovery
import com.ldl.ireader.base.fragment.BaseRootFragment
import com.ldl.ireader.base.viewmodel.LoadState
import com.ldl.ireader.base.viewmodel.SuccessState
import com.ldl.ireader.ui.adapter.DiscoveryAdapter
import com.ldl.ireader.viewmodel.DiscoveryViewModel
import kotlinx.android.synthetic.main.fragment_discovery.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 作者：LDL 创建时间：2020/9/9
 * 类说明：发现
 */
class DiscoveryFragment : BaseRootFragment() {

    private val mViewModel by viewModel<DiscoveryViewModel>()
    private lateinit var mAdapter: DiscoveryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_discovery, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getLayoutId(): Int = fragment_discovery

    override fun initToolbar() {
        toolbar.title = StringUtils.getString(R.string.discovery)
        mActivity.setSupportActionBar(toolbar)
    }

    override fun initView() {
        initRecyclerView()
        initSwipeRefreshLayout()
        initLoadMore()
    }

    override fun initData() {
        mViewModel.getDiscovery(1)
        observe()
    }


    private fun initRecyclerView() {
        rv_discovery.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = DiscoveryAdapter(mViewModel.discoveryList)
        rv_discovery.adapter = mAdapter
        mAdapter.setOnItemClickListener { _, _, position ->

        }
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener {
            mViewModel.onRefresh()
        }
    }

    private fun initLoadMore() {
        mAdapter.loadMoreModule.apply {
            isEnableLoadMoreIfNotFullPage = false
            setOnLoadMoreListener {
                mViewModel.onLoadMore()
            }
        }
    }

    private fun observe() {
        mViewModel.mStateLiveData.observe(this, Observer {
            when (it) {
                SuccessState -> {
                    swipeRefreshLayout.isRefreshing = false
                }
                LoadState -> {

                }
                else -> {
                    swipeRefreshLayout.isRefreshing = false
                    mAdapter.loadMoreModule.loadMoreComplete()
                }
            }
        })
        mViewModel.discoveryLiveData.observe(this, Observer {
            if (mViewModel.pageNum == 1) {
                mViewModel.discoveryList.clear()
                mAdapter.notifyDataSetChanged()
            } else {
                mAdapter.loadMoreModule.loadMoreComplete()
            }
            mAdapter.addData(it)
        })
    }

}