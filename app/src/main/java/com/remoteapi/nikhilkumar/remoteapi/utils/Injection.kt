package com.remoteapi.nikhilkumar.remoteapi.utils

import android.app.Application
import com.remoteapi.nikhilkumar.remoteapi.repo.RemoteDataSource
import com.remoteapi.nikhilkumar.remoteapi.repo.Repository
import com.remoteapi.nikhilkumar.remoteapi.repo.RepositoryImpl

object Injection {

    fun provideRepository(app: Application): Repository {
        val remoteDataSource = provideChannelsRemoteDataSource(app)

        return RepositoryImpl(remoteDataSource)
    }

    private fun provideChannelsRemoteDataSource(app: Application): RemoteDataSource = RemoteDataSource.getInstance(app)



}