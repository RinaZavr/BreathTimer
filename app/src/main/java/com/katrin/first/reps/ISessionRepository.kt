package com.katrin.first.reps

import com.katrin.first.model.BreathSession

interface ISessionRepository {
    fun saveSessionParameters(session: BreathSession)
    fun getSessionParameters(): BreathSession
    fun saveMetronom(value: Boolean)
    fun getMetronom(): Boolean
}