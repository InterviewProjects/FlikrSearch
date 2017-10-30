package com.example.sawaiparihar.myflikrsearch

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.sawaiparihar.myflikrsearch.greenrobotevents.SearchEvents
import kotlinx.android.synthetic.main.fragment_search.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

/**
 * Created by sawai.parihar on 30/10/17.
 */
class SearchFragment : Fragment(), LifecycleOwner {
    @Inject
    lateinit var mSearchPresenter: SearchPresenter

    @Inject
    lateinit var mEventBus: EventBus

    @Inject
    lateinit  var mAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity.application as FlikrSearchApplication).appComponent!!.inject(this)

        lifecycle.addObserver(mSearchPresenter)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (view!!.findViewById<View>(R.id.search_et) as EditText).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                mEventBus.post(SearchEvents.SearchKeywordEvent(charSequence.toString()))
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        mRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        mRecyclerView!!.adapter = mAdapter

    }

    override fun onResume() {
        super.onResume()

        mEventBus.register(this)
    }

    override fun onPause() {
        super.onPause()

        mEventBus.unregister(this)
    }

    @Subscribe
    fun onEvent(event: SearchEvents.SearchKeywordResultEvent) {
        val array = event.data.getAsJsonObject("photos").getAsJsonArray("photo")

        mAdapter.setJsonArray(array)
        mAdapter.notifyDataSetChanged()
    }

    @Subscribe
    fun showProgress(event: SearchEvents.SearchShowProgress) {
        mProgressBar!!.visibility = View.VISIBLE
    }

    @Subscribe
    fun hideProgress(event: SearchEvents.SearchHideProgress) {
        mProgressBar!!.visibility = View.GONE
    }
}