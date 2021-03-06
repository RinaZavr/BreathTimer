package com.katrin.first.repeatScreen.viewModel

import android.os.CountDownTimer
import com.katrin.first.model.BreathSession
import com.katrin.first.model.RepeatFragmentState
import com.katrin.first.repeatScreen.sound.ISoundManager
import com.katrin.first.reps.ISessionRepository
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class RepeatViewModel @Inject constructor(
    private val soundManager: ISoundManager,
    private val rep: ISessionRepository
): IRepeatViewModel {
    private var sessionParameter = BreathSession()
    private var currentState = RepeatFragmentState()
    private var timer: CountDownTimer? = null
    override var state = PublishSubject.create<RepeatFragmentState>()
    private var copyParams = BreathSession()

    init {
        currentState = currentState.copy(metronomValue = rep.getMetronom())
    }

    override fun startSession(parameters: BreathSession) {
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

                if(currentState.metronomValue) soundManager.playSound()
            }

            override fun onFinish() {}
        }
        timer?.start()
    }

    override fun onDestroy() {
        timer?.cancel()
    }

    override fun restartSession() {
        sessionParameter = copyParams
        timer?.start()
        currentState = currentState.copy(buttonEnable = false)
        state.onNext(currentState) //?????????????????? ?????????????????? ?????????????????? (???????????????????? ????????????????)
    }

    override fun soundOnOff() {
        when (currentState.metronomValue) {
            true -> currentState = currentState.copy(metronomValue = false)
            false -> currentState = currentState.copy(metronomValue = true)
        }
        state.onNext(currentState)
        rep.saveMetronom(currentState.metronomValue)
    }
}