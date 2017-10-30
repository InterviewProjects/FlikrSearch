package com.example.sawaiparihar.myflikrsearch.dagger2

import com.example.sawaiparihar.myflikrsearch.SearchApiService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by sawai.parihar on 30/10/17.
 */
@Module
class NetworkModule {

    @Provides
    @Named(NAME_BASE_URL)
    fun provideBaseUrlString(): String {
        return "https://www.flickr.com/"
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): Converter.Factory {
        val gson = GsonBuilder()
                .setLenient()
                .create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(converter: Converter.Factory,
                        rxJavaCallAdapterFactory: RxJava2CallAdapterFactory, @Named(NAME_BASE_URL) baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build()
    }

    @Provides
    @Singleton
    fun provideUsdaApi(retrofit: Retrofit): SearchApiService {
        return retrofit.create(SearchApiService::class.java)
    }

    companion object {
        private const val NAME_BASE_URL = "NAME_BASE_URL"
    }
}