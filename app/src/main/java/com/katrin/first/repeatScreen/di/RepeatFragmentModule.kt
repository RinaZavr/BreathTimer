package com.katrin.first.repeatScreen.di

import android.content.Context
import com.katrin.first.repeatScreen.sound.ISoundManager
import com.katrin.first.repeatScreen.sound.SoundManager
import com.katrin.first.repeatScreen.viewModel.IRepeatViewModel
import com.katrin.first.repeatScreen.viewModel.RepeatViewModel
import com.katrin.first.reps.ISessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepeatFragmentModule {
    @Provides
    @Singleton
    fun provideRepeatViewModel(
        soundManager: ISoundManager,
        rep: ISessionRepository
    ): IRepeatViewModel = RepeatViewModel(soundManager, rep)

    @Provides
    @Singleton
    fun provideSoundManager(
        @ApplicationContext
        context: Context
    ): ISoundManager = SoundManager(context)
}