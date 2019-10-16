package br.com.filipe.presentation.ui.base

import androidx.paging.PageKeyedDataSource
import br.com.filipe.domain.exception.DefaultException
import br.com.filipe.domain.model.Paging
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class BasePagingDataSource<Value>(
    private val compositeDisposable: CompositeDisposable,
    private val listener: Listener? = null
) : PageKeyedDataSource<Int, Value>() {

    interface Listener {
        fun initialEmpty(empty: Boolean)
        fun initialLoading(show: Boolean)
        fun initialLoadError(error: Throwable)
        fun pagingLoading(show: Boolean)
        fun pagingLoadError(error: Throwable)
    }

    private var pageCount: Int? = null

    private var retry: (() -> Unit)? = null

    abstract fun fetchData(search: String, page: Int, pageSize: Int): Single<Paging<Value>>

    protected open fun initialPage(): Int = 1

    protected open fun searchTitleMovie(): String = "avengers"

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        initialCallback: LoadInitialCallback<Int, Value>
    ) {
        val pageSize = params.requestedLoadSize

        compositeDisposable.add(
            fetchData(searchTitleMovie(), initialPage(), pageSize)
                .doOnSubscribe { listener?.initialLoading(true) }
                .subscribe(
                    {
                        pageCount = it.pageCount
                        initialCallback.onResult(it.results, null, initialPage().inc())
                        listener?.initialLoading(false)
                        listener?.initialEmpty(it.results.isEmpty())
                    },
                    { listener?.initialLoadError(getDefaultException(it)) }
                )
        )
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Value>
    ) {
        val page = params.key
        val pageSize = params.requestedLoadSize

        if (!hasNextPage(page))
            return

        compositeDisposable.add(
            fetchData("", page, pageSize)
                .doOnSubscribe { listener?.pagingLoading(true) }
                .subscribe(
                    {
                        callback.onResult(it.results, page.inc())
                        listener?.pagingLoading(false)
                    },
                    {
                        retry = { loadAfter(params, callback) }
                        listener?.pagingLoadError(getDefaultException(it))
                    }
                )
        )

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Value>
    ) {
        //EMPTY
    }

    fun retryErrorPage() {
        retry?.invoke()
    }

    private fun getDefaultException(throwable: Throwable): DefaultException {
        return when (throwable) {
            is DefaultException -> throwable
            else -> DefaultException()
        }
    }

    private fun hasNextPage(page: Int) = pageCount?.let { it > page } == true
}