package run.moku.framework.adapter.validator

import run.moku.framework.api.exception.ApiException
import run.moku.framework.api.response.ApiResponseCode

fun throwIf(condition: Boolean, errorCode: ApiResponseCode) {
    if (condition) throw ApiException(errorCode)
}

fun throwIf(condition: Boolean, exception: Throwable) {
    if (condition) throw exception
}

fun throwUnless(condition: Boolean, errorCode: ApiResponseCode) {
    if (!condition) throw ApiException(errorCode)
}

fun throwUnless(condition: Boolean, exception: Throwable) {
    if (!condition) throw exception
}