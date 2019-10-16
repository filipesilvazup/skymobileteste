package br.com.filipe.presentation.ui.custom.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.filipe.presentation.R
import br.com.filipe.presentation.databinding.ItemPagingLoadingBinding

class ProgressViewHolder(
    private val binding: ItemPagingLoadingBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): ProgressViewHolder {
            return ProgressViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_paging_loading,
                    parent,
                    false
                )
            )
        }
    }
}