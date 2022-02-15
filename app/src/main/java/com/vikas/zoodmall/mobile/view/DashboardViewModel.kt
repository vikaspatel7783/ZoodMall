package com.vikas.zoodmall.mobile.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikas.zoodmall.mobile.data.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel: ViewModel() {

    private val dataRepository = DataRepository()

    private val _api1Response = MutableLiveData<ResultStatus>()
    private val api1Response: LiveData<ResultStatus> = _api1Response

    fun callApi1(): LiveData<ResultStatus> {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.getApi1Data().let {
                _api1Response.postValue(it)
            }
        }
        return api1Response
    }
}