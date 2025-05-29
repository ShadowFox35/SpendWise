package com.example.spendwise.ui.common.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<
        State : Any,
        Event : BaseEvent,
        Effect : BaseEffect,
        > : ViewModel() {

    private val _state: MutableStateFlow<State> by lazy { MutableStateFlow(initialState()) }
    val state: StateFlow<State> = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    private val _effect = Channel<Effect>(Channel.BUFFERED)
    val effectFlow: Flow<Effect> = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            _event.collect { handleEvent(it) }
        }
    }

    protected abstract fun initialState(): State
    protected abstract fun handleEvent(event: Event)

    fun updateState(reducer: State.() -> State) {
        _state.update { it.reducer() }
    }

    fun submitEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    fun submitEffect(effect: Effect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}
