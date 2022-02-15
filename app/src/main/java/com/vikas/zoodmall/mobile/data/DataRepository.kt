package com.vikas.zoodmall.mobile.data

import com.vikas.zoodmall.mobile.network.ZoodMallService
import com.vikas.zoodmall.mobile.view.Api1UIModel
import com.vikas.zoodmall.mobile.view.ResultStatus
import java.util.*

class DataRepository {

    private val zoodMallService = ZoodMallService.create()

    suspend fun getApi1Data(): ResultStatus {
        try {
            val api1Model = zoodMallService.callApi1()

            if (api1Model.ErrorCode != "9999" && api1Model.Message.toUpperCase(Locale.ROOT) != "SUCCESS") {
                throw Exception("Api1 returned failure code")
            }

            val api1UIModels = mutableListOf<Api1UIModel>()
            api1Model.data.mainBanner.forEach { mainBanner ->
                api1UIModels.add(Api1UIModel(mainBanner.id, mainBanner.imageUrl))
            }
            api1Model.data.brandZoneBanner.forEach { brandZoneBanner ->
                api1UIModels.add(Api1UIModel(brandZoneBanner.id, brandZoneBanner.imageUrl))
            }
            api1Model.data.promotionalBanner.forEach { promotionalBanner ->
                api1UIModels.add(Api1UIModel(promotionalBanner.id, promotionalBanner.imageUrl))
            }
            api1Model.data.promotionalBanner2.forEach { promotionalBanner2 ->
                api1UIModels.add(Api1UIModel(promotionalBanner2.id, promotionalBanner2.imageUrl))
            }
            return ResultStatus.Success(data = api1UIModels)
        } catch (ex: Exception) {
            return ResultStatus.Failure(ex)
        }
    }
}