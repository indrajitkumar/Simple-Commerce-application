package com.elililly.codingchallage.utils

import android.annotation.SuppressLint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.elililly.codingchallage.models.Product

class DataBindingUtility {
    companion object DataBindingAdapter {
        @BindingAdapter("image_url")
        @JvmStatic
        fun loadImage(imageView: View?, image_url: String?) {
            if (image_url != null) {
                val imageView = imageView as ImageView
                val image: Drawable = getImageFromDrawable(imageView.context, image_url)
                imageView.setImageDrawable(image)
            }
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("discount")
        @JvmStatic
        fun discount(textV: View?, product: Product?) {
            if (product != null) {
                val textView = textV as TextView
                val actualPrice = product.price.value.toFloat()
                val discountPercent = product.price.discount.toInt()
                val discountPrice = actualPrice - ((actualPrice * discountPercent)/100)
                textView.text = "Rs: $discountPrice"
            }
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("actualPrice")
        @JvmStatic
        fun actualPrice(textV: View?, product: Product?) {
            if (product != null) {
                val textView = textV as TextView
                textView.text = "Rs: ${product.price.value.toFloat()}"
                textView.paintFlags = STRIKE_THRU_TEXT_FLAG
            }
        }
    }
}