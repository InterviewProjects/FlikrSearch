package com.example.sawaiparihar.myflikrsearch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class FlikrSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, SearchFragment())
                .commit()
    }
}
