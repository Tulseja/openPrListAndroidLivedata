package com.remoteapi.nikhilkumar.remoteapi.utils

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.remoteapi.nikhilkumar.remoteapi.repo.Repository
import com.remoteapi.nikhilkumar.remoteapi.viewModel.MyContestListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
        private val application: Application,
        private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(MyContestListViewModel::class.java) -> MyContestListViewModel(repository)
                    else -> throw IllegalArgumentException("Unknown viewmodel class ${modelClass.name}")
                }
            } as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) = INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE
                            ?: ViewModelFactory(
                                    application, Injection.provideRepository(application)
                            )
                                    .also { INSTANCE = it }
                }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}