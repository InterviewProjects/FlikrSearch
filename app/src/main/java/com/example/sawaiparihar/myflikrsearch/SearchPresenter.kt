package com.example.sawaiparihar.myflikrsearch

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import com.example.sawaiparihar.myflikrsearch.greenrobotevents.SearchEvents
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by sawai.parihar on 30/10/17.
 */
class SearchPresenter(context: Context) : LifecycleObserver {
    @Inject
    lateinit var mEventBus: EventBus

    @Inject
    lateinit var mSearchApiService: SearchApiService

    @Inject
    lateinit var mCompositeDisposable: CompositeDisposable

    private var mSearchKeywordPublisher: PublishSubject<String>? = null

    init {
        (context as FlikrSearchApplication).appComponent!!.inject(this)

        initializeSearchKeywordPublisher()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun presenterOnResume() {
        registerEventBus()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun presenterOnPause() {
        unRegisterEventBus()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun presenterOnDestroy() {
        mCompositeDisposable.dispose()
    }

    private fun registerEventBus() {
        mEventBus.register(this)
    }

    private fun unRegisterEventBus() {
        mEventBus.unregister(this)
    }

    @Subscribe
    fun onEvent(event: SearchEvents.SearchKeywordEvent) {
        mSearchKeywordPublisher!!.onNext(event.keyword)
    }

    private fun initializeSearchKeywordPublisher() {
        mSearchKeywordPublisher = PublishSubject.create()

        mCompositeDisposable.add(mSearchKeywordPublisher!!
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<String> { this.sendKeywordToApi(it) }))
    }

    private fun sendKeywordToApi(keyword: String) {
        mEventBus.post(SearchEvents.SearchShowProgress())

        val queryMap = HashMap<String, String>()
        queryMap.put("tags", keyword)

        mCompositeDisposable.add(mSearchApiService.getSearchResult(queryMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<JsonObject>() {
                    override fun onNext(jsonObject: JsonObject) {
                        mEventBus.post(SearchEvents.SearchHideProgress())

                        println("hello")

                        mEventBus.post(SearchEvents.SearchKeywordResultEvent(jsonObject))
                    }

                    override fun onError(e: Throwable) {
                        println("err")
                    }

                    override fun onComplete() {
                        println("err")
                    }
                }))

    }
}