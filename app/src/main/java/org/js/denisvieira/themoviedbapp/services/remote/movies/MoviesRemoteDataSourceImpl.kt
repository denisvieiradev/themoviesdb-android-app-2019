package org.js.denisvieira.themoviedbapp.services.remote.movies

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.js.denisvieira.themoviedbapp.domain.data.Cache
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import org.js.denisvieira.themoviedbapp.services.BaseRemoteDataSource.RemoteDataSourceCallback

class MoviesRemoteDataSourceImpl(private val mMoviesApiDataSource: MoviesApiDataSource) : MoviesRemoteDataSource {

    @SuppressLint("CheckResult")
    override fun searchMovies(queryText: String, page: Int, callback: RemoteDataSourceCallback<List<Movie>>) {
        mMoviesApiDataSource.searchMovies(queryText, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { callback.isLoading(true) }
                .doAfterTerminate { callback.isLoading(false) }
                .subscribe(
                        { response ->
                            val moviesWithGenres = mapMovieWithGenres(response.body()!!.results)
                            callback.onSuccess(moviesWithGenres)
                        },
                        { throwable ->
                            callback.onError(throwable.localizedMessage)
                        }
                )
    }


    @SuppressLint("CheckResult")
    override fun getUpcomingMovies(page: Int, callback: RemoteDataSourceCallback<List<Movie>>) {
        mMoviesApiDataSource.upcomingMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { callback.isLoading(true) }
                .doAfterTerminate { callback.isLoading(false) }
                .subscribe(
                        { response ->
                            val moviesWithGenres = mapMovieWithGenres(response.body()!!.results)
                            callback.onSuccess(moviesWithGenres)
                        },
                        { throwable ->
                            callback.onError(throwable.localizedMessage)
                        }
                )
    }

    @SuppressLint("CheckResult")
    override fun getMovie(id: Int, callback: RemoteDataSourceCallback<Movie>) {
        mMoviesApiDataSource.movie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { callback.isLoading(true) }
                .doAfterTerminate { callback.isLoading(false) }
                .subscribe(
                        {
                            callback.onSuccess(it.body()!!)
                        },
                        { throwable ->
                            callback.onError(throwable.localizedMessage)
                        }
                )
    }

    private fun mapMovieWithGenres(results: List<Movie>): List<Movie> {
        return results.map { movie ->
            movie.copy(genres = Cache.genres.filter { movie.genreIds?.contains(it.id) == true })
        }
    }


}