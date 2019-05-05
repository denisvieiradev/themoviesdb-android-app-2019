package org.js.denisvieira.themoviedbapp.domain.usecases

interface UseCase {

    interface UseCaseCallback<R> {
        fun onSuccess(response: R)
        fun onEmptyData()
        fun isLoading(isLoading: Boolean)
        fun onError(errorDescription: String)
    }

    interface VoidUseCaseCallback {
        fun onSuccess()
        fun onEmptyData()
        fun isLoading(isLoading: Boolean)
        fun onError(errorDescription: String)
    }

}