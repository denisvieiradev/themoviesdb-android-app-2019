package org.js.denisvieira.themoviedbapp.application.helper.viewmodelobserver

import androidx.lifecycle.MutableLiveData

abstract class BaseObservers<T, LD : MutableLiveData<T>>(
    val onSuccessMainDataObserver: LD,
    val onErrorMainDataObserver: MutableLiveData<String> = MutableLiveData(),
    val isLoadingMainDataObserver: MutableLiveData<Boolean> = MutableLiveData()
)