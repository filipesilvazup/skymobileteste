package br.com.filipe.presentation.ui.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.databinding.ObservableField

fun <T> LiveData<T>.observeNotNull(
    owner: LifecycleOwner,
    result: (T) -> Unit
) {
    this.observe(owner, Observer {
        it?.let(result)
    })
}

fun ObservableField<String>.getNotNull() = this.get() ?: ""