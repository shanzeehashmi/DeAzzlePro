package com.deazzle.deazzleproject.data.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.deazzle.deazzleproject.R

@BindingAdapter("profile_pic_url")
fun setUserImage(imgView: ImageView, imgUrl: String?){

        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder_man)
                   )
            .into(imgView)

}