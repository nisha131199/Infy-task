package com.infosys.di

import android.app.Application
import com.infosys.data.domain.datasource.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideDataSource(
        context: Application
    ): DataSource {
        return DataSource(context)
    }
}