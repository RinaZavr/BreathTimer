package com.katrin.first.mainScreen.di

import android.content.Context
import com.katrin.first.repeatScreen.viewModel.IRepeatViewModel
import com.katrin.first.repeatScreen.viewModel.RepeatViewModel
import com.katrin.first.reps.ISessionRepository
import com.katrin.first.reps.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainScreenModule {

    @Provides
    @Singleton
    fun provideStorage(
        @ApplicationContext context: Context
    ): ISessionRepository = SessionRepository(context)
}