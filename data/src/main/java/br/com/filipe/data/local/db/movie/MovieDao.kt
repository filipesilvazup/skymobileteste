package br.com.filipe.data.local.db.movie

import android.arch.persistence.room.*
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favoriteMovieEntity: FavoriteMovieEntity)

    @Delete
    fun deleteFavorite(favoriteMovieEntity: FavoriteMovieEntity)

    @Query("SELECT * FROM favorite_movie WHERE id = :id LIMIT 1")
    fun findFavorite(id: Long): FavoriteMovieEntity?

    @Query("SELECT * FROM favorite_movie")
    fun findFavorites(): Single<List<FavoriteMovieEntity>>

}