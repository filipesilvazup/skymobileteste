package br.com.filipe.presentation.ui.base

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.filipe.presentation.ui.custom.viewholder.PageErrorViewHolder
import br.com.filipe.presentation.ui.custom.viewholder.ProgressViewHolder


abstract class BasePagedListAdapter<T> (
    itemDiff: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, RecyclerView.ViewHolder>(itemDiff) {

    companion object {
        private const val TYPE_ITEM = 0
        private const val TYPE_LOADING = 1
        private const val TYPE_ERROR = 2
    }

    interface OnRetryPageListener {
        fun onRetryErrorPage()
    }

    var retryListener: OnRetryPageListener? = null

    private var pagingLoading = false

    private var pagingError = false

    abstract fun itemViewHolder(parent: ViewGroup): PagedItemViewHolder

    protected open fun onItemViewHolder(holder: PagedItemViewHolder, item: T, position: Int) {
        //EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> itemViewHolder(parent)

            TYPE_LOADING -> ProgressViewHolder.create(parent)

            TYPE_ERROR -> PageErrorViewHolder.create(parent) { retryListener?.onRetryErrorPage() }

            else -> throw IllegalArgumentException("Viewtype not found")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PagedItemViewHolder) {
            getItem(position)?.let { onItemViewHolder(holder, it, position) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            pagingLoading && position == itemCount.minus(1) -> TYPE_LOADING
            pagingError && position == itemCount.minus(1) -> TYPE_ERROR
            else -> TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (pagingLoading || pagingError) 1 else 0
    }

    override fun submitList(pagedList: PagedList<T>) {
        if (pagingLoading || pagingError) {
            pagingLoading = false
            pagingError = false
            notifyDataSetChanged()
        }

        super.submitList(pagedList)
    }

    fun notifyPagingLoading(show: Boolean) {
        pagingLoading = show
        if (show) pagingError = false
        notifyDataSetChanged()
    }

    fun notifyPageError() {
        if (super.getItemCount() > 0) {
            pagingError = true
            pagingLoading = false
            notifyDataSetChanged()
        }
    }

}

abstract class PagedItemViewHolder(view: View) : RecyclerView.ViewHolder(view)