package org.js.denisvieira.themoviedbapp.application.modules.moviedetail

import org.js.denisvieira.themoviedbapp.application.helper.BaseViewModel
import org.js.denisvieira.themoviedbapp.application.injections.InjectionUseCase.provideGetMovieDetailUsecase
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import org.js.denisvieira.themoviedbapp.domain.usecases.UseCase.UseCaseCallback

class MovieDetailViewModel : BaseViewModel<Movie>() {

    private val mGetMovieGenresUsecase = provideGetMovieDetailUsecase()

     fun loadMovieDetail(id: Int) {
         mGetMovieGenresUsecase.getMovieDetail(id, object : UseCaseCallback<Movie> {

            override fun onSuccess(response: Movie) {
                onSuccessMainDataObserver.value = response
            }

            override fun isLoading(isLoading: Boolean) {
                isLoadingMainDataObserver.value = isLoading
            }

            override fun onError(errorDescription: String) {
                onErrorMainDataObserver.value = errorDescription
            }

        })
    }

}
