package com.begicim.singking.util

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.begicim.singking.R
import com.begicim.singking.ui.character.ApiStatus
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url: String?) {
    url?.let {
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView)
            .load(imgUri)
            .into(imageView)
    }
}

@BindingAdapter("statusImage")
fun bindApiStatusImage(statusImage: ImageView, apiStatus: ApiStatus?) {
    apiStatus?.let { apiStatus ->
        when (apiStatus) {
            ApiStatus.ERROR -> {
                statusImage.visibility = View.VISIBLE
                statusImage.setImageResource(R.drawable.ic_connection_error)
            }
            ApiStatus.SUCCESS -> {
                statusImage.visibility = View.GONE
            }
            ApiStatus.LOADING -> {
                statusImage.visibility = View.VISIBLE
                statusImage.setImageResource(R.drawable.loading_animation)
            }
        }
    }
}