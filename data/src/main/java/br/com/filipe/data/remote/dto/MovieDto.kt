package br.com.filipe.data.remote.dto

import br.com.filipe.domain.model.Movie
import com.google.gson.annotations.SerializedName

/**
 * Created by Murilo Moro on 01/02/19.
 */
sealed class MovieDto {

    data class Response(
        val id: String,
        val title: String,
        val overview: String,
        val duration: String,
        @SerializedName("release_year") val year: String,
        @SerializedName("cover_url") val imageUrl: String
        //@SerializedName("backdrops_url") val posterPath: ArrayList<String>
    ) {

        fun toMovie() = Movie(
            id,
            title,
            year,
            imageUrl,
            false
        )

    }
}