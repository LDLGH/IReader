package com.ldl.ireader.util.loader

import android.widget.ImageView

/**
 * 作者：LDL 创建时间：2020/9/9
 * 类说明：
 */
interface ImageHelper {

    /**
     * 加载图片
     * @param imageView imageView
     * @param url 图片地址
     * */
    fun loadImage(imageView: ImageView, url: String?)

    /**
     * 加载圆角图片
     * @param imageView imageView
     * @param url 图片地址
     * @param radius 圆角角度
     * */
    fun loadImageRound(imageView: ImageView, url: String?, radius: Int)

    /**
     * 加载圆形图片
     * @param imageView imageView
     * @param url 图片地址
     * */
    fun loadImageCircle(imageView: ImageView, url: String?)
}