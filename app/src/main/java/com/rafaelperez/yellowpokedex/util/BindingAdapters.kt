package com.rafaelperez.yellowpokedex.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rafaelperez.yellowpokedex.R

@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    if(url!=null) {
        Glide.with(imageView.context).load(url).into(imageView)
    } else {
        Glide.with(imageView.context).load(R.drawable.ic_launcher_foreground).into(imageView)
    }
}