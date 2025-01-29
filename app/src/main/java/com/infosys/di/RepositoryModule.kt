package com.infosys.di

import com.infosys.data.domain.datasource.DataSource
import com.infosys.data.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        dataSource: DataSource
    ): Repository {
        return Repository(dataSource)
    }
}