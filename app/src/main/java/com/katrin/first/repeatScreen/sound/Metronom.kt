package com.katrin.first.repeatScreen.sound

import android.net.Uri
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class Metronom : MediaBrowserServiceCompat() {
    private var player: ExoPlayer? = null
    private var soundSession: MediaSessionCompat? = null
    private var soundSessionCallback = object : MediaSessionCompat.Callback() {
        override fun onPlayFromUri(uri: Uri?, extras: Bundle?) {
            super.onPlayFromUri(uri, extras)
            val mediaSource = MediaItem.fromUri(uri!!)
            play(mediaSource)
        }

        override fun onStop() {
            super.onStop()

        }
    }

    override fun onCreate() {
        super.onCreate()
        createPlayer()
        soundSession = MediaSessionCompat(baseContext, "breath timer session")
        soundSession?.setCallback(soundSessionCallback)
        soundSession?.isActive = true
    }

    private fun createPlayer() {
        player = ExoPlayer.Builder(this).build()
        player?.playWhenReady = true
    }

    private fun play(mediaSource: MediaItem) {
        player?.setMediaItem(mediaSource)
        player?.prepare()
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        TODO("Not yet implemented")
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        TODO("Not yet implemented")
    }

}