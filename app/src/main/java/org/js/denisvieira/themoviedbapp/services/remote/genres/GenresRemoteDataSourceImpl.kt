package org.js.denisvieira.themoviedbapp.services.remote.genres

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import org.js.denisvieira.themoviedbapp.services.BaseRemoteDataSource.RemoteDataSourceCallback

class GenresRemoteDataSourceImpl(private val mGenresApiDataSource: GenresApiDataSource) : GenresRemoteDataSource {

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