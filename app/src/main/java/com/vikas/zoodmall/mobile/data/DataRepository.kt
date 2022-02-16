package com.vikas.zoodmall.mobile.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vikas.zoodmall.mobile.network.ZoodMallService
import com.vikas.zoodmall.mobile.view.ApiUIModel
import com.vikas.zoodmall.mobile.view.ResultStatus
import java.util.*

class DataRepository {

    suspend fun getApi1Data(zoodMallService: ZoodMallService): ResultStatus {
        return try {
            val api1Model = zoodMallService.callApi1()

            if (api1Model.ErrorCode != "9999" && api1Model.Message.toUpperCase(Locale.ROOT) != "SUCCESS") {
                throw Exception("Api1 returned failure code")
            }

            val api1UIModels = mutableListOf<ApiUIModel>()
            api1Model.data.mainBanner.forEach { mainBanner ->
                api1UIModels.add(ApiUIModel(mainBanner.id, mainBanner.imageUrl))
            }
            api1Model.data.brandZoneBanner.forEach { brandZoneBanner ->
                api1UIModels.add(ApiUIModel(brandZoneBanner.id, brandZoneBanner.imageUrl))
            }
            api1Model.data.promotionalBanner.forEach { promotionalBanner ->
                api1UIModels.add(ApiUIModel(promotionalBanner.id, promotionalBanner.imageUrl))
            }
            api1Model.data.promotionalBanner2.forEach { promotionalBanner2 ->
                api1UIModels.add(ApiUIModel(promotionalBanner2.id, promotionalBanner2.imageUrl))
            }
            ResultStatus.Success(data = api1UIModels)
        } catch (ex: Exception) {
            ResultStatus.Failure(ex)
        }
    }

    fun getApi2Data(zoodMallService: ZoodMallService): Api2PagingSource {
        return Api2PagingSource(zoodMallService)
    }

    class Api2PagingSource(private val zoodMallService: ZoodMallService) : PagingSource<Int, ApiUIModel>() {

        override fun getRefreshKey(state: PagingState<Int, ApiUIModel>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiUIModel> {
            val position = params.key ?: 1
            try {
                val api2Model = zoodMallService.callApi2(page = position)

                if (api2Model.ErrorCode != "9999" && api2Model.Message.toUpperCase(Locale.ROOT) != "SUCCESS") {
                    throw Exception("Api2 returned failure code")
                }

                val api2UIModels = mutableListOf<ApiUIModel>()
                api2Model.data.marketList.forEach { market ->
                    api2UIModels.add(ApiUIModel(id = market.productId, imageUrl = market.imgUrl))
                }
                return LoadResult.Page(
                    data = api2UIModels,
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = position + 1
                )

            } catch (ex: Exception) {
                return LoadResult.Error(throwable = ex)
            }
        }

    }

}