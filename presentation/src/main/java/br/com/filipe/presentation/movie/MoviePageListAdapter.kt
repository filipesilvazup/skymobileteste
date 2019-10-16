package br.com.filipe.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.model.Movies
import br.com.filipe.presentation.R
import br.com.filipe.presentation.databinding.ItemMovieBinding
import br.com.filipe.presentation.ui.base.BasePagedListAdapter
import br.com.filipe.presentation.ui.base.PagedItemViewHolder

class MoviePageListAdapter : BasePagedListAdapter<Movie>(itemDiff) {

    var listener: OnMovieClickListener? = null

    interface OnMovieClickListener {
        fun onClickFavoriteMovie(movie: Movie)
    }


    companion object {
        val itemDiff = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                old: Movie,
                new: Movie
            ) = old.id == new.id

            override fun areContentsTheSame(
                old: Movie,
                new: Movie
            ) = old == new
        }
    }

    override fun itemViewHolder(parent: ViewGroup): PagedItemViewHolder {
        return MoviesItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {

            when (holder) {
                is MoviesItemViewHolder -> holder.bind(it, listener)
            }
        }
    }

    class MoviesItemViewHolder(private val binding: ItemMovieBinding) :
        PagedItemViewHolder(binding.root) {

        fun bind(
            movie: Movie,
            listener: OnMovieClickListener?
        ) {
            binding.movie = movie

            binding.clRoot.setOnClickListener { listener?.onClickFavoriteMovie(movie) }

            binding.executePendingBindings()
        }
    }
}