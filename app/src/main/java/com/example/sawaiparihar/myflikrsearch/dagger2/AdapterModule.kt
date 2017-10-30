package com.example.sawaiparihar.myflikrsearch.dagger2

import android.content.Context
import android.view.LayoutInflater
import com.example.sawaiparihar.myflikrsearch.SearchAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by sawai.parihar on 30/10/17.
 */
@Module
class AdapterModule {
    @Provides
    fun provideSearchScreenAdapter(context: Context): SearchAdapter {
        return SearchAdapter(context)
    }

    @Provides
    fun provideLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }
}