package com.example.sawaiparihar.myflikrsearch.greenrobotevents

import com.google.gson.JsonObject

/**
 * Created by sawai.parihar on 30/10/17.
 */
class SearchEvents {
    class SearchKeywordEvent(val keyword: String)
    class SearchKeywordResultEvent(val data: JsonObject)
    class SearchShowProgress
    class SearchHideProgress
}