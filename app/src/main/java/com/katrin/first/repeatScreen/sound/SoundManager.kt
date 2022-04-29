package com.katrin.first.repeatScreen.sound

import android.content.Context
import android.media.MediaPlayer
import javax.inject.Inject

class SoundManager @Inject constructor(
    private val context: Context
) : ISoundManager {
    private val descriptor = context.assets.openFd("heart_attack.mp3")
    private val player = MediaPlayer().apply {
        setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
        prepare()
        setVolume(1f, 1f)
    }

    override fun playSound() {
        player.start()
    }
}