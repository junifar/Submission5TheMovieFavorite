package com.rubahapi.themoviefavorite

import android.content.ContentValues
import android.content.UriMatcher
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private val sURIMatcher = UriMatcher(UriMatcher.NO_MATCH)
    private val TVSHOWS = 1
    private val TVSHOWS_ID = 2

    companion object {
        const val AUTHORITY = "com.rubahapi.moviedb.provider.TVShowProvider"
        const val TABLE_TV_SHOW = "tv_show_db"
        val _ID = "ID"
        val TITLE = "title"
        val OVERVIEW = "overview"
        val POSTER_PATH = "poster_path"
        val CONTENT_URI_TV_SHOW: Uri = Uri.parse("content://" + AUTHORITY + "/" +
                TABLE_TV_SHOW)
    }

    init {
        sURIMatcher.addURI(AUTHORITY,
                TABLE_TV_SHOW, TVSHOWS)
        sURIMatcher.addURI(AUTHORITY, "$TABLE_TV_SHOW/#",
                TVSHOWS_ID)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val args = ContentValues()
        args.put(_ID, 9)
        args.put(TITLE, "Test Luar Akses")
        args.put(OVERVIEW, "Test")
        args.put(POSTER_PATH, "Test")

        val urxx = contentResolver.insert(CONTENT_URI_TV_SHOW, args)
        val id = urxx.pathSegments[1]
        println(id)
    }
}
