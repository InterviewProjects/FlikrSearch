package com.example.sawaiparihar.myflikrsearch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.adapter_search_item.view.*
import javax.inject.Inject

/**
 * Created by sawai.parihar on 30/10/17.
 */
class SearchAdapter(context: Context) : RecyclerView.Adapter<SearchAdapter.SearchHolder>() {
    @Inject
    lateinit var mLayoutInflater: LayoutInflater

    @Inject
    lateinit var mContext: Context

    private var mDataArray: JsonArray? = null


    init {
        (context as FlikrSearchApplication).appComponent?.inject(this)

        mDataArray = JsonArray()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(mLayoutInflater.inflate(R.layout.adapter_search_item, parent, false))
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return mDataArray!!.size()
    }

    fun setJsonArray(jsonArray: JsonArray) {
        this.mDataArray = jsonArray
    }

    inner class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val data = mDataArray!!.get(position).asJsonObject

            val photoUrl = String.format("https://farm%s.staticflickr.com/%s/%s_%s_%s.jpg", data.get("farm").asString,
                    data.get("server").asString, data.get("id").asString, data.get("secret").asString, "z")

            Glide.with(mContext)
                    .load(photoUrl)
                    .into(itemView.mSearchIv)
        }
    }
}