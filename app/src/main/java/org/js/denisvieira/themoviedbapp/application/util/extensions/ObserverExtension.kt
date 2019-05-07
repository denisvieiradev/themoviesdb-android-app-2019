package org.js.denisvieira.themoviedbapp.application.util.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import br.com.stant.obras.application.utils.events.Event

inline fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, crossinline onEventUnhandledContent: (T) -> Unit) {
    observe(owner, Observer { it?.getEventIfNotHandled()?.let(onEventUnhandledContent) })
}