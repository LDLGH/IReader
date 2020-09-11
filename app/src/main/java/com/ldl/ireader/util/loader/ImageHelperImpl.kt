package com.ldl.ireader.util.loader

import android.widget.ImageView
import com.blankj.utilcode.util.ConvertUtils
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.ldl.ireader.util.GlideApp
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * 作者：LDL 创建时间：2020/9/9
 * 类说明：
 */
class ImageHelperImpl : ImageHelper {

    companion object {
        const val BASE_IMG_URL = "http://pt.yuenov.com:15555/"
    }

    override fun loadImage(imageView: ImageView, url: String?) {
        GlideApp.with(imageView.context)
            .load(BASE_IMG_URL + url)
            .into(imageView)
    }

    override fun loadImageRound(imageView: ImageView, url: String?, radius: Int) {
        GlideApp.with(imageView.context)
            .load(BASE_IMG_URL + url)
            .transform(
                CenterCrop(), RoundedCornersTransformation(
                    ConvertUtils.dp2px(radius.toFloat()), 0)
            )
            .into(imageView)
    }

    override fun loadImageCircle(imageView: ImageView, url: String?) {
        GlideApp.with(imageView.context)
            .load(BASE_IMG_URL + url)
            .circleCrop()
            .into(imageView)
    }
}