package com.colors.game.di

import com.colors.game.domain.GreedySolver
import com.colors.game.domain.LevelGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGreedySolver(): GreedySolver = GreedySolver()

    @Provides
    @Singleton
    fun provideLevelGenerator(solver: GreedySolver): LevelGenerator = LevelGenerator(solver)
}
