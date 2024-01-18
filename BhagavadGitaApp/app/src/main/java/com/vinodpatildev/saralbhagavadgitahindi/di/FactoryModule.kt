package com.vinodpatildev.saralbhagavadgitahindi.di

import android.app.Application
import com.vinodpatildev.saralbhagavadgitahindi.repository.Repository
import com.vinodpatildev.saralbhagavadgitahindi.utils.DataStoreRepository
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.VersesFragmentViewModelFactory
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.ChaptersFragmentViewModelFactory
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.SplashViewModelFactory
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.VerseDetailsFragmentViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun providesSplashViewModelFactory(
        app : Application,
        dataStoreRepository: DataStoreRepository,
        repository: Repository
    ) : SplashViewModelFactory {
        return SplashViewModelFactory(
            app,
            dataStoreRepository,
            repository
        )
    }

    @Singleton
    @Provides
    fun providesChaptersFragmentViewModelFactory(
        app : Application,
        dataStoreRepository: DataStoreRepository,
        repository: Repository
    ) : ChaptersFragmentViewModelFactory {
        return ChaptersFragmentViewModelFactory(
            app,
            dataStoreRepository,
            repository
        )
    }

    @Singleton
    @Provides
    fun providesChapterVersesFragmentViewModelFactory(
        app : Application,
        dataStoreRepository: DataStoreRepository,
        repository: Repository
    ) : VersesFragmentViewModelFactory {
        return VersesFragmentViewModelFactory(
            app,
            dataStoreRepository,
            repository
        )
    }
}