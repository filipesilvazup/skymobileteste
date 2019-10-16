package br.com.filipe.presentation.ui.custom.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.filipe.presentation.R
import br.com.filipe.presentation.databinding.ItemPageErrorBinding

class PageErrorViewHolder(
    private val binding: ItemPageErrorBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, onRetry: () -> Unit): PageErrorViewHolder {
            val pageError = PageErrorViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_page_error,
                    parent,
                    false
                )
            )
            pageError.binding.clRoot.setOnClickListener { onRetry.invoke() }
            return pageError
        }
    }

}