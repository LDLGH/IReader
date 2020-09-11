package com.ldl.ireader.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ldl.ireader.base.viewmodel.BaseViewModel
import com.ldl.ireader.base.viewmodel.ErrorState
import com.ldl.ireader.base.viewmodel.SuccessState
import com.ldl.ireader.data.http.convertHttpRes
import com.ldl.ireader.data.http.handlingApiExceptions
import com.ldl.ireader.data.http.handlingExceptions
import com.ldl.ireader.data.http.handlingHttpResponse
import com.ldl.ireader.model.net.BookDetailRes
import com.ldl.ireader.model.net.RecommendRes
import com.ldl.ireader.repository.BookDetailRepository

/**
 * 作者：LDL 创建时间：2020/9/11
 * 类说明：
 */
class BookDetailViewModel(private val mBookDetailRepository: BookDetailRepository) :
    BaseViewModel() {

    val bookDetailLiveData = MutableLiveData<BookDetailRes>()

    val recommendLiveData = MutableLiveData<RecommendRes>()

    fun getDetail(bookId: Int) = launchOnIO(
        tryBlock = {
            mBookDetailRepository.getDetail(bookId).run {
                handlingHttpResponse<BookDetailRes>(
                    convertHttpRes(),
                    successBlock = {
                        bookDetailLiveData.postValue(it)
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

    fun getRecommend(pageNum: Int, bookId: Int) = launchOnIO(
        tryBlock = {
            mBookDetailRepository.getRecommend(pageNum, bookId).run {
                handlingHttpResponse<RecommendRes>(
                    convertHttpRes(),
                    successBlock = {
                        recommendLiveData.postValue(it)
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

}