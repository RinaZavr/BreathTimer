package com.katrin.first.model

import java.io.Serializable

data class BreathSession(
    val time: Int = 0,
    val firstBreath: Int = 0,
    val firstDelay: Int = 0,
    val secondBreath: Int = 0,
    val secondDelay: Int = 0
) : Serializable
