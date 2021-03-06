package br.com.filipe.domain.model

import java.io.Serializable

data class Movie(
    val id: String,
    val title: String,
    val year: String,
    val imageUrl: String,
    val description: String,
    val posterPath: ArrayList<String>
) : Serializable {

    fun getImage() = posterPath[0]
}