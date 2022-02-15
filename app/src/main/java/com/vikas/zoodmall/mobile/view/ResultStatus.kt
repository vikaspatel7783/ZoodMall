package com.vikas.zoodmall.mobile.view

sealed class ResultStatus {

    data class Success(val data: List<ApiUIModel>): ResultStatus()

    data class Failure(val exception: Exception): ResultStatus()
}