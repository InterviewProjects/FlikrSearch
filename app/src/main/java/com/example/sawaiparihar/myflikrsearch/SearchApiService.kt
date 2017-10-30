package com.example.sawaiparihar.myflikrsearch

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by sawai.parihar on 30/10/17.
 */
interface SearchApiService {
    @GET("services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=83a0a54bb953d2cb277c8a13bfbd18db")
    fun getSearchResult(@QueryMap options: Map<String, String>): Observable<JsonObject>

}