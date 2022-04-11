package com.katrin.first.repeatScreen.viewModel

import android.os.CountDownTimer
import com.katrin.first.model.BreathSession
import com.katrin.first.model.RepeatFragmentState
import io.reactivex.subjects.PublishSubject

class RepeatViewModel {
    private var sessionParameter = BreathSession()
    private var currentState = RepeatFragmentState()
    private var timer: CountDownTimer? = null
    val state = PublishSubject.create<RepeatFragmentState>()
    private var copyParams = BreathSession()

    fun startSession(parameters: BreathSession) {
        sessionParameter = parameters
        copyParams = parameters.copy()

        timer = object : CountDownTimer((sessionParameter.time * 1000).toLong(), 1000) {
            override fun onTick(p0: Long) {
                sessionParameter = sessionParameter.copy(time = sessionParameter.time - 1)

                if (sessionParameter.firstBreath != 0) {
                    sessionParameter = sessionParameter.copy(firstBreath = sessionParameter.firstBreath - 1)
                }
                else if (sessionParameter.firstDelay != 0) {
                    sessionParameter = sessionParameter.copy(firstDelay = sessionParameter.firstDelay - 1)
                }
                else if (sessionParameter.secondBreath != 0) {
                    sessionParameter = sessionParameter.copy(secondBreath = sessionParameter.secondBreath - 1)
                }
                else if (sessionParameter.secondDelay != 0) {
                    sessionParameter = sessionParameter.copy(secondDelay = sessionParameter.secondDelay - 1)
                }
                else {
                    sessionParameter = sessionParameter.copy(
                        firstBreath = copyParams.firstBreath,
                        firstDelay = copyParams.firstDelay,
                        secondBreath = copyParams.secondBreath,
                        secondDelay = copyParams.secondDelay
                    )
                }

                currentState = currentState.copy(
                    time = sessionParameter.time,
                    firstBreath = sessionParameter.firstBreath,
                    secondBreath = sessionParameter.secondBreath,
                    firstDelay = sessionParameter.firstDelay,
                    secondDelay = sessionParameter.secondDelay
                )

                if (sessionParameter.time == 0) {
                    currentState = currentState.copy(buttonEnable = true)
                }

                state.onNext(currentState)
            }

            override fun onFinish() {}
        }
        timer?.start()
    }

    fun onDestroy() {
        timer?.cancel()
    }

    fun restartSession() {
        sessionParameter = copyParams
        timer?.start()
        currentState = currentState.copy(buttonEnable = false)
        state.onNext(currentState) //отправить сообщение фрагменту (пожирателю подписки)
    }

}