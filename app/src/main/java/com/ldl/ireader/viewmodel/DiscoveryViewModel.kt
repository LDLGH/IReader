package com.ldl.ireader.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ldl.ireader.base.viewmodel.BaseViewModel
import com.ldl.ireader.base.viewmodel.ErrorState
import com.ldl.ireader.base.viewmodel.SuccessState
import com.ldl.ireader.data.http.convertHttpRes
import com.ldl.ireader.data.http.handlingApiExceptions
import com.ldl.ireader.data.http.handlingExceptions
import com.ldl.ireader.data.http.handlingHttpResponse
import com.ldl.ireader.model.bean.DiscoveryModel
import com.ldl.ireader.model.net.DiscoveryRes
import com.ldl.ireader.repository.DiscoveryRepository

/**
 * 作者：LDL 创建时间：2020/9/8
 * 类说明：
 */
class DiscoveryViewModel(private val mDiscoveryRepository: DiscoveryRepository) : BaseViewModel() {

    val discoveryLiveData = MutableLiveData<List<DiscoveryModel>>()

    val discoveryList by lazy {
        ArrayList<DiscoveryModel>()
    }

    var pageNum = 1

    fun getDiscovery(pageNum: Int) = launchOnIO(
        tryBlock = {
            mDiscoveryRepository.getDiscovery(pageNum).run {
                handlingHttpResponse<DiscoveryRes>(
                    convertHttpRes(),
                    successBlock = {
                        discoveryLiveData.postValue(it.list)
                        mStateLiveData.postValue(SuccessState)
                    },
                    failureBlock = {
                        handlingApiExceptions(it)
                        mStateLiveData.postValue(ErrorState(it.errorMsg))
                    }
                )
            }
        },
        catchBlock = { e ->
            handlingExceptions(e)
            mStateLiveData.postValue(ErrorState(e.message))
        }
    )

    fun onRefresh() {
        pageNum = 1
        getDiscovery(pageNum)
    }

    fun onLoadMore() {
        pageNum++
        getDiscovery(pageNum)
    }
}