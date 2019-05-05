package org.js.denisvieira.themoviedbapp.application.modules.selectmovie

import androidx.lifecycle.ViewModel
import br.com.stant.obras.application.utils.events.Event
import br.com.stant.obras.application.utils.events.SingleLiveEvent
import org.js.denisvieira.themoviedbapp.application.helper.viewmodelobserver.BaseObserversWithSingleEventOnSuccess
import org.js.denisvieira.themoviedbapp.application.injections.InjectionUseCase.provideGetMovieGenresUsecase
import org.js.denisvieira.themoviedbapp.application.injections.InjectionUseCase.provideGetPopularMovies
import org.js.denisvieira.themoviedbapp.application.injections.InjectionUseCase.provideSearchMoviesUseCase
import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import org.js.denisvieira.themoviedbapp.domain.usecases.UseCase.UseCaseCallback

class SelectMovieViewModel : ViewModel() {

    private val mGetPopularMoviesUsecase  = provideGetPopularMovies()
    private val mGetMovieGenresUsecase    = provideGetMovieGenresUsecase()
    private val mSearchMoviesUseCase      = provideSearchMoviesUseCase()

    val mMainSelectMovieObservers = BaseObserversWithSingleEventOnSuccess<List<Movie>>()
    val loadGenresSuccessObserver = SingleLiveEvent<List<Genre>>()

    fun searchMovies(queryText: String, page: Int) {
        mSearchMoviesUseCase.searchMovies(queryText, page, object : UseCaseCallback<List<Movie>> {
            override fun onSuccess(response: List<Movie>) {
                mMainSelectMovieObservers.onSuccessMainDataObserver.value = Event(response)
            }

            override fun isLoading(isLoading: Boolean) {
                mMainSelectMovieObservers.isLoadingMainDataObserver.value = isLoading
            }

            override fun onError(errorDescription: String) {
                mMainSelectMovieObservers.onErrorMainDataObserver.value = errorDescription
            }

        })
    }

    fun loadPopularMovies(page: Int) {
        mGetPopularMoviesUsecase.getMovies(page, object : UseCaseCallback<List<Movie>> {
            override fun onSuccess(response: List<Movie>) {
                mMainSelectMovieObservers.onSuccessMainDataObserver.value = Event(response)
            }

            override fun isLoading(isLoading: Boolean) {
                mMainSelectMovieObservers.isLoadingMainDataObserver.value = isLoading
            }

            override fun onError(errorDescription: String) {
                mMainSelectMovieObservers.onErrorMainDataObserver.value = errorDescription
            }

        })
    }

    fun loadMovieGenres() {
        mGetMovieGenresUsecase.getMovieGenres(object : UseCaseCallback<List<Genre>> {
            override fun onSuccess(response: List<Genre>) {
                loadGenresSuccessObserver.value = response
            }

            override fun isLoading(isLoading: Boolean) {
                mMainSelectMovieObservers.isLoadingMainDataObserver.value = isLoading
            }

            override fun onError(errorDescription: String) {
                mMainSelectMovieObservers.onErrorMainDataObserver.value = errorDescription
            }

        })
    }
}
