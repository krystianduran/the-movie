package com.kraigdev.themovie.core.di.common.google

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.kraigdev.themovie.core.auth.GoogleAuthUiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideGoogleAuthUiClient(
        @ApplicationContext context: Context
    ): GoogleAuthUiClient = GoogleAuthUiClient(
        context = context,
        oneTapClient = Identity.getSignInClient(context)
    )
}
