package com.ldl.ireader.util.loader

import android.widget.ImageView

/**
 * 作者：LDL 创建时间：2020/9/9
 * 类说明：图片加载器
 */
object ImageLoader : ImageHelper {

    private val mImageHelper by lazy { ImageHelperImpl() }

    override fun loadImage(imageView: ImageView, url: String?) =
        mImageHelper.loadImage(imageView, url)

    override fun loadImageRound(imageView: ImageView, url: String?, radius: Int) =
        mImageHelper.loadImageRound(imageView, url, radius)

    override fun loadImageCircle(imageView: ImageView, url: String?) =
        mImageHelper.loadImageCircle(imageView, url)
}