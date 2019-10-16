package br.com.filipe.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PagingDto<T>(
    @SerializedName("Search") val content: List<MovieDto.MovieResponse>,
    val totalPages: Int? = null,
    @SerializedName("totalResults") val totalElements: Int? = null
)