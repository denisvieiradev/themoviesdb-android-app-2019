package org.js.denisvieira.themoviedbapp.services.remote.genres

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers
import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import org.js.denisvieira.themoviedbapp.services.BaseRemoteDataSource.RemoteDataSourceCallback
import org.js.denisvieira.themoviedbapp.services.remote.movies.MoviesRemoteDataSourceImpl

class GenresRemoteDataSourceImpl(private val mGenresApiDataSource: GenresApiDataSource) : GenresRemoteDataSource {

    companion object {

        @Volatile
        private var INSTANCE: MoviesRemoteDataSourceImpl? = null

        fun getInstance(@NonNull genresApiDataSource: GenresApiDataSource): MoviesRemoteDataSourceImpl =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MoviesRemoteDataSourceImpl(genresApiDataSource).also {
                    INSTANCE = it
                }
            }
    }

    @SuppressLint("CheckResult")
    override fun getMovieGenres(callback: RemoteDataSourceCallback<List<Genre>>) {
        mGenresApiDataSource.movieGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            callback.onSuccess(it.body()!!.genres)
                        },
                        { throwable ->
                            callback.onError(throwable.localizedMessage)
                        }
                )
    }


}