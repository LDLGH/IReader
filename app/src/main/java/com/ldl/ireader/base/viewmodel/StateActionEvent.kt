package com.ldl.ireader.base.viewmodel

/**
 * 作者：LDL 创建时间：2020/9/8
 * 类说明：
 */
//定义网络请求状态(密封类扩展性更好)
sealed class StateActionEvent

object LoadState : StateActionEvent()

object SuccessState : StateActionEvent()

class ErrorState(val message: String?) : StateActionEvent()