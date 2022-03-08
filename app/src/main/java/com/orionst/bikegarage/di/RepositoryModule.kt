package com.orionst.bikegarage.di

import com.orionst.bikegarage.domain.BikeRepository
import com.orionst.bikegarage.data.repository.BikeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindBikeRepo(bikeRepositoryImpl: BikeRepositoryImpl): BikeRepository
}