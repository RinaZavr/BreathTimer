package com.katrin.first.repeatScreen.viewModel

import com.katrin.first.model.BreathSession
import com.katrin.first.model.RepeatFragmentState
import io.reactivex.Observable

interface IRepeatViewModel {
    val state: Observable<RepeatFragmentState>
    fun startSession(parameters: BreathSession)
    fun onDestroy()
    fun restartSession()
}