package com.vikas.zoodmall.mobile.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

class ImageDownloader {

     suspend fun download(url: URL): Bitmap {
        return BitmapFactory.decodeStream(url.openConnection().getInputStream())
    }
}