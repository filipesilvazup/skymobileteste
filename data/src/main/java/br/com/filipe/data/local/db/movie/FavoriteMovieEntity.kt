package br.com.filipe.data.local.db.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movie")
data class FavoriteMovieEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val description: String,
    val image: String
)