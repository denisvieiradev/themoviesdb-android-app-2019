package org.js.denisvieira.themoviedbapp.application.helper.viewmodelobserver

import br.com.stant.obras.application.utils.events.Event
import br.com.stant.obras.application.utils.events.SingleLiveEvent

class BaseObserversWithSingleEventOnSuccess<T> : BaseObservers<Event<T>, SingleLiveEvent<Event<T>>>(
    onSuccessMainDataObserver = SingleLiveEvent()
)