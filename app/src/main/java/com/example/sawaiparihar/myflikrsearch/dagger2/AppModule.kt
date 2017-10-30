package com.example.sawaiparihar.myflikrsearch.dagger2

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by sawai.parihar on 30/10/17.
 */
@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }
}