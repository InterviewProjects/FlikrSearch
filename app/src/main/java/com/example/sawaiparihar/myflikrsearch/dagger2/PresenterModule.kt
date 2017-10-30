package com.example.sawaiparihar.myflikrsearch.dagger2

import android.content.Context
import com.example.sawaiparihar.myflikrsearch.SearchPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by sawai.parihar on 30/10/17.
 */
@Module
class PresenterModule {
    @Provides
    fun provideSearchPresenter(context: Context): SearchPresenter {
        return SearchPresenter(context)
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}