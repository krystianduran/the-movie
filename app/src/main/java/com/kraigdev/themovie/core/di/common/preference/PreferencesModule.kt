package com.kraigdev.themovie.core.di.common.preference

import android.content.Context
import com.kraigdev.themovie.modules.common.data.GeneralPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideGeneralPreferences(
        @ApplicationContext context: Context
    ) = GeneralPreferences(context = context)
}
