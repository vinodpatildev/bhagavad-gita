package com.vinodpatildev.saralbhagavadgitahindi.di

import android.app.Application
import com.vinodpatildev.saralbhagavadgitahindi.repository.Repository
import com.vinodpatildev.saralbhagavadgitahindi.utils.DataStorePrefManager
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.ChapterVersesFragmentViewModelFactory
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.ChaptersFragmentViewModelFactory
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.SplashViewModelFactory
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.VerseActivityViewModelFactory
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
        dataStorePrefManager: DataStorePrefManager,
        repository: Repository
    ) : SplashViewModelFactory {
        return SplashViewModelFactory(
            app,
            dataStorePrefManager,
            repository
        )
    }

    @Singleton
    @Provides
    fun providesChaptersFragmentViewModelFactory(
        app : Application,
        dataStorePrefManager: DataStorePrefManager,
        repository: Repository
    ) : ChaptersFragmentViewModelFactory {
        return ChaptersFragmentViewModelFactory(
            app,
            dataStorePrefManager,
            repository
        )
    }

    @Singleton
    @Provides
    fun providesChapterVersesFragmentViewModelFactory(
        app : Application,
        dataStorePrefManager: DataStorePrefManager,
        repository: Repository
    ) : ChapterVersesFragmentViewModelFactory {
        return ChapterVersesFragmentViewModelFactory(
            app,
            dataStorePrefManager,
            repository
        )
    }

    @Singleton
    @Provides
    fun providesVerseActivityViewModelFactory(
        app : Application,
        dataStorePrefManager: DataStorePrefManager,
        repository: Repository
    ) : VerseActivityViewModelFactory {
        return VerseActivityViewModelFactory(
            app,
            dataStorePrefManager,
            repository
        )
    }
}