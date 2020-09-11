package com.ldl.ireader.data.http

import com.blankj.utilcode.util.ToastUtils
import com.google.gson.JsonParseException
import com.ldl.ireader.base.BaseResponse
import com.ldl.ireader.constant.Constant.Companion.HTTP_SUCCESS
import kotlinx.coroutines.CancellationException
import java.net.SocketTimeoutException

/**
 * 作者：LDL 创建时间：2020/9/9
 * 类说明：
 */
/**
 * 处理请求层的错误,对可能的已知的错误进行处理
 */
fun handlingExceptions(e: Throwable) {
    when (e) {
        is CancellationException -> {
        }
        is SocketTimeoutException -> {
        }
        is JsonParseException -> {
        }
        else -> {
        }
    }
}

// 简单说明:密封类结合when让可能情况都是已知的,代码维护性更高。
sealed class HttpResponse

data class Success<out T>(val data: T) : HttpResponse()
data class Failure(val error: HttpError) : HttpResponse()

/**
 * 处理响应层的错误
 */
fun handlingApiExceptions(e: HttpError) {
    ToastUtils.showLong(e.errorMsg)
}

enum class HttpError(val code: Int, val errorMsg: String?) {
    USER_CREATE(101, "新用户创建成功"),
    QUERY_ERROR(102, "未查询到数据"),
    PARAMS_ERROR(1001, "参数校验出错"),
    VALUE_ERROR(1002, "返回值异常"),
    ILLEGAL_REQUEST(1003, "非法请求"),
    AUTHORITY_ERROR(1005, "权限验证异常"),
    SERVICE_TIMEOUT(1007, "远程调用服务超时"),
    SYSTEM_ERROR(9999, "系统出错"),
}


/**
 * 处理HttpResponse
 * @param res
 * @param successBlock 成功
 * @param failureBlock 失败
 */
fun <T> handlingHttpResponse(
    res: HttpResponse,
    successBlock: (data: T) -> Unit,
    failureBlock: ((error: HttpError) -> Unit)? = null,
) {
    when (res) {
        is Success<*> -> {
            successBlock.invoke(res.data as T)
        }
        is Failure -> {
            with(res) {
                failureBlock?.invoke(error) ?: defaultErrorBlock.invoke(error)
            }
        }
    }
}


// 默认的处理方案
val defaultErrorBlock: (error: HttpError) -> Unit = { error ->
    ToastUtils.showLong(error.errorMsg ?: "${error.code}")            // 可以根据是否为debug进行拆分处理
}

fun <T : Any> BaseResponse<T>.convertHttpRes(): HttpResponse {
    return if (this.result.code == HTTP_SUCCESS) {
        data?.let {
            Success(it)
        } ?: Success(Any())
    } else {
        when (result.code) {
            101 -> {
                Failure(HttpError.USER_CREATE)
            }
            102 -> {
                Failure(HttpError.QUERY_ERROR)
            }
            1001 -> {
                Failure(HttpError.PARAMS_ERROR)
            }
            1002 -> {
                Failure(HttpError.VALUE_ERROR)
            }
            1003 -> {
                Failure(HttpError.ILLEGAL_REQUEST)
            }
            1005 -> {
                Failure(HttpError.AUTHORITY_ERROR)
            }
            1007 -> {
                Failure(HttpError.SERVICE_TIMEOUT)
            }
            9999 -> {
                Failure(HttpError.SYSTEM_ERROR)
            }
            else ->
                Failure(HttpError.SYSTEM_ERROR)

        }
    }
}

