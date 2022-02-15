package com.vikas.zoodmall.mobile.view

sealed class ResultStatus {

    data class Success(val data: List<Api1UIModel>): ResultStatus()

    data class Failure(val exception: Exception): ResultStatus()
}