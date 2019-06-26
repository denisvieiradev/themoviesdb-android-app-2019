package org.js.denisvieira.themoviedbapp.application.helper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<T>: ViewModel() {

    val onSuccessMainDataObserver   = MutableLiveData<T>()
    var onEmptyMainDataObserver     = MutableLiveData<String>()
    val onErrorMainDataObserver     = MutableLiveData<String>()
    val isLoadingMainDataObserver   = MutableLiveData<Boolean>()

}