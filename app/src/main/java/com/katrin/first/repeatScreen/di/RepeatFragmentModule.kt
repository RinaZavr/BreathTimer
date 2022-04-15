package com.katrin.first.repeatScreen.di

import com.katrin.first.repeatScreen.viewModel.IRepeatViewModel
import com.katrin.first.repeatScreen.viewModel.RepeatViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepeatFragmentModule {
    @Provides
    @Singleton
    fun provideRepeatViewModel(): IRepeatViewModel = RepeatViewModel()
}