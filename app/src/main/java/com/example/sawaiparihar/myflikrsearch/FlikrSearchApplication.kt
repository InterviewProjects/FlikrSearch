package com.example.sawaiparihar.myflikrsearch

import android.app.Application
import com.example.sawaiparihar.myflikrsearch.dagger2.AppComponent
import com.example.sawaiparihar.myflikrsearch.dagger2.AppModule
import com.example.sawaiparihar.myflikrsearch.dagger2.DaggerAppComponent

/**
 * Created by sawai.parihar on 30/10/17.
 */
class FlikrSearchApplication : Application() {
    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }


    private fun initDagger(application: FlikrSearchApplication): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build()
    }
}
