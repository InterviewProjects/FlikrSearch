package com.example.sawaiparihar.myflikrsearch.dagger2

import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

/**
 * Created by sawai.parihar on 30/10/17.
 */
@Module
class EventBusModule {
    @Provides
    @Singleton
    fun provideEventBus(): EventBus {
        return EventBus.getDefault()
    }
}