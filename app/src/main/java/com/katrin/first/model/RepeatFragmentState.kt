package com.katrin.first.model

data class RepeatFragmentState(
    val time: Int = 0,
    val firstBreath: Int = 0,
    val firstDelay: Int = 0,
    val secondBreath: Int = 0,
    val secondDelay: Int = 0,
    val buttonEnable: Boolean = false,
    val metronomValue: Boolean = false
)
