package com.example.modelviewmodel.model.methodbinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import retrofit2.http.Url

class imageBinding {

    constructor()

    companion object {
        @BindingAdapter("imagen")
        @JvmStatic
        fun setImagenUrl(imageView: ImageView, imageUrl: String?) {
            Picasso.get().load("http://192.168.1.248:8080/images/$imageUrl.jpg")
                .into(imageView)
        }
    }
}