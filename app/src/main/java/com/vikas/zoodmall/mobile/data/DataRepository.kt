package com.vikas.zoodmall.mobile.data

import com.vikas.zoodmall.mobile.network.ZoodMallService
import com.vikas.zoodmall.mobile.view.ApiUIModel
import com.vikas.zoodmall.mobile.view.ResultStatus
import java.util.*

class DataRepository {

    private val zoodMallService = ZoodMallService.create()

    suspend fun getApi1Data(): ResultStatus {
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

    suspend fun getApi2Data(): ResultStatus {
        return try {
            val api2Model = zoodMallService.callApi2()

            if (api2Model.ErrorCode != "9999" && api2Model.Message.toUpperCase(Locale.ROOT) != "SUCCESS") {
                throw Exception("Api2 returned failure code")
            }

            val api2UIModels = mutableListOf<ApiUIModel>()
            api2Model.data.marketList.forEach { market ->
                api2UIModels.add(ApiUIModel(id = market.productId, imageUrl = market.imgUrl))
            }
            ResultStatus.Success(data = api2UIModels)
        } catch (ex: Exception) {
            ResultStatus.Failure(ex)
        }
    }
}