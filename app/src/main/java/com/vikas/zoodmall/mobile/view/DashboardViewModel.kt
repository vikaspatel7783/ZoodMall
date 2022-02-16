package com.vikas.zoodmall.mobile.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vikas.zoodmall.mobile.data.DataRepository
import com.vikas.zoodmall.mobile.network.ZoodMallService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DashboardViewModel: ViewModel() {

    private val dataRepository = DataRepository()
    private val zoodMallService = ZoodMallService.create()

    private val _api1Response = MutableLiveData<ResultStatus>()
    private val api1Response: LiveData<ResultStatus> = _api1Response

    fun callApi1(): LiveData<ResultStatus> {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.getApi1Data(zoodMallService).let {
                _api1Response.postValue(it)
            }
        }
        return api1Response
    }

    fun callApi2Pagination(): Flow<PagingData<ApiUIModel>> {
        return Pager(PagingConfig(pageSize = 30)) {
            dataRepository.getApi2Data(zoodMallService)
        }.flow
            .cachedIn(viewModelScope)
    }

}