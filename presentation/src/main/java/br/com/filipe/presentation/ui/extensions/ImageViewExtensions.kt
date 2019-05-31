package br.com.filipe.presentation.ui.extensions

import android.support.v7.widget.AppCompatImageView
import com.squareup.picasso.Picasso

fun AppCompatImageView.loadImageUrl(imageUrl: String) {
    try {
        Picasso.get()
            .load(imageUrl)
            .into(this)
    } catch (ex: Exception) {
    }
}