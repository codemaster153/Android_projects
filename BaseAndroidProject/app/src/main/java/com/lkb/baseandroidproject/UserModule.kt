package com.lkb.baseandroidproject

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UserModule {
    @Provides
    @SQLRepo
    fun provideSQLRepository(): UserRepository {
        return SQLRepository()
    }

    @Provides
    @FirebaseRepo
    fun provideFirebaseRepository(): UserRepository {
        return FirebaseRepository()
    }
}