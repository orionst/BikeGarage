package com.orionst.bikegarage.di

import com.orionst.bikegarage.domain.BikeRepository
import com.orionst.bikegarage.data.repository.BikeRepositoryImpl
import com.orionst.bikegarage.data.repository.ComponentsRepositoryImpl
import com.orionst.bikegarage.domain.ComponentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindBikeRepo(bikeRepositoryImpl: BikeRepositoryImpl): BikeRepository

    @Binds
    fun bindComponentsRepo(
        componentsRepositoryImpl: ComponentsRepositoryImpl
    ): ComponentsRepository
}
