package com.shawn.githubdemo.di

import com.shawn.githubdemo.model.source.repository.list.ListRepository
import com.shawn.githubdemo.model.source.repository.list.ListRepositoryImpl
import com.shawn.githubdemo.model.source.repository.user.UserRepository
import com.shawn.githubdemo.model.source.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepoListRepository(impl: ListRepositoryImpl): ListRepository

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}