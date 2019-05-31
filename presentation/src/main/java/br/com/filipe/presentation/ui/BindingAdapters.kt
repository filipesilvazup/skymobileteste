package br.com.filipe.presentation.ui

import android.databinding.BindingAdapter
import android.support.v7.widget.AppCompatImageView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import br.com.filipe.presentation.ui.extensions.loadImageUrl

/**
 * Created by Murilo Moro on 27/01/19.
 */
object BindingAdapters {

    @JvmStatic
    @BindingAdapter("show")
    fun View.show(show: Boolean) {
        visibility = if (show) VISIBLE else GONE
    }

    @JvmStatic
    @BindingAdapter("loadImageUrl")
    fun AppCompatImageView.loadUrl(imageUrl: String) {
        this.loadImageUrl(imageUrl)
    }


}