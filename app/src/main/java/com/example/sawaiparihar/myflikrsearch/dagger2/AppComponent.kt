package com.example.sawaiparihar.myflikrsearch.dagger2

import com.example.sawaiparihar.myflikrsearch.SearchAdapter
import com.example.sawaiparihar.myflikrsearch.SearchFragment
import com.example.sawaiparihar.myflikrsearch.SearchPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by sawai.parihar on 30/10/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, AdapterModule::class, EventBusModule::class, NetworkModule::class, PresenterModule::class))
interface AppComponent {
    fun inject(target: SearchAdapter)
    fun inject(target: SearchFragment)
    fun inject(target: SearchPresenter)
}