package com.ldl.ireader.base.viewmodel

import androidx.lifecycle.*
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.*

/**
 * 作者：LDL 创建时间：2020/9/8
 * 类说明：
 */
//类型别名
typealias LaunchBlock = suspend CoroutineScope.() -> Unit

typealias EmitBlock<T> = suspend LiveDataScope<T>.() -> T

typealias Cancel = (e: Exception) -> Unit

open class BaseViewModel : ViewModel() {

    val mStateLiveData = MutableLiveData<StateActionEvent>()//通用事件模型驱动(如：显示对话框、取消对话框、错误提示)

    fun launch(cancel: Cancel? = null, block: LaunchBlock) {//使用协程封装统一的网络请求处理
        viewModelScope.launch {
            //ViewModel自带的viewModelScope.launch,会在页面销毁的时候自动取消请求,有效封装内存泄露
            try {
                mStateLiveData.value = LoadState
                block()
                mStateLiveData.value = SuccessState
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> cancel?.invoke(e)
                    else -> mStateLiveData.value = ErrorState(e.message)
                }
            }
        }
    }

    fun <T> emit(cancel: Cancel? = null, block: EmitBlock<T>): LiveData<T> = liveData {
        try {
            mStateLiveData.value = LoadState
            emit(block())
            mStateLiveData.value = SuccessState
        } catch (e: Exception) {
            LogUtils.e(e.message)
            when (e) {
                is CancellationException -> cancel?.invoke(e)
                else -> mStateLiveData.value = ErrorState(e.message)
            }
        }
    }

    /**
     * @param tryBlock 尝试执行的挂起代码块
     * @param catchBlock 捕获异常的代码块 "协程对Retrofit的实现在失败、异常时没有onFailure的回调而是直接已Throwable的形式抛出"
     * @param finallyBlock finally代码块
     */
    private suspend fun tryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(e: Throwable) -> Unit,
        finallyBlock: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                tryBlock()
            } catch (e: Throwable) {
                catchBlock(e)
            } finally {
                finallyBlock()
            }
        }
    }

    /**
     * 在主线程中开启
     * catchBlock、finallyBlock 并不是必须,不同的业务对于错误的处理也可能不同想要完全统一的处理是很牵强的
     */
    fun launchOnMain(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(e: Throwable) -> Unit = {},             // 默认空实现，可根据具体情况变化
        finallyBlock: suspend CoroutineScope.() -> Unit = {}
    ) {
        viewModelScope.launch {
            tryCatch(tryBlock, catchBlock, finallyBlock)
        }
    }
    /**
     * 在IO线程中开启,修改为Dispatchers.IO
     */
    fun launchOnIO(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(e: Throwable) -> Unit = {},
        finallyBlock: suspend CoroutineScope.() -> Unit = {}
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            tryCatch(tryBlock, catchBlock, finallyBlock)
        }
    }


}