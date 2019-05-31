package br.com.filipe.domain.model

/**
 * Created by Murilo Moro on 31/01/19.
 */
data class Movie(
    val id: String,
    val title: String,
    val year: String,
    val posterPath: String,
    val favorite: Boolean
) {

    fun getImageUrl() = posterPath
}