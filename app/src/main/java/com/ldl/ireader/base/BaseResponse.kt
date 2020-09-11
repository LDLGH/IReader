package com.ldl.ireader.base

import com.ldl.ireader.model.ResultModel

/**
 * 作者：LDL 创建时间：2020/9/9
 * 类说明：
 */
data class BaseResponse<out T>(val result: ResultModel, val data: T?) {
}