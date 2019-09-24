package com.rubahapi.themoviefavorite.resolver

import android.content.ContentResolver
import android.content.ContentValues
import android.content.UriMatcher
import android.net.Uri
import android.provider.BaseColumns
import com.rubahapi.themoviefavorite.MainActivity
import com.rubahapi.themoviefavorite.model.TvShow
import com.rubahapi.themoviefavorite.resolver.TVShowResolver.TvShowColumns.Companion.OVERVIEW
import com.rubahapi.themoviefavorite.resolver.TVShowResolver.TvShowColumns.Companion.POSTER_PATH
import com.rubahapi.themoviefavorite.resolver.TVShowResolver.TvShowColumns.Companion.TITLE
import com.rubahapi.themoviefavorite.resolver.TVShowResolver.TvShowColumns.Companion._ID

class TVShowResolver{
    private val sURIMatcher = UriMatcher(UriMatcher.NO_MATCH)
    private val TVSHOWS = 1
    private val TVSHOWS_ID = 2

    companion object{
        const val AUTHORITY = "com.rubahapi.moviedb.provider.TVShowProvider"
        const val TABLE_TV_SHOW = "tv_show_db"
        val CONTENT_URI_TV_SHOW: Uri = Uri.parse("content://" + AUTHORITY + "/" +
                TABLE_TV_SHOW)
    }

    internal class TvShowColumns : BaseColumns {
        companion object {
            val _ID = "ID"
            val TITLE = "title"
            val OVERVIEW = "overview"
            val POSTER_PATH = "poster_path"
        }
    }

    init {
        sURIMatcher.addURI(
            AUTHORITY,
            TABLE_TV_SHOW, TVSHOWS)
        sURIMatcher.addURI(
            AUTHORITY, "${TABLE_TV_SHOW}/#",
            TVSHOWS_ID)
    }

    fun insertTvShow(contentResolver:ContentResolver, tvShow: TvShow):String {
        val args = ContentValues()
        args.put(MainActivity._ID, 9)
        args.put(MainActivity.TITLE, "Test Luar Akses")
        args.put(MainActivity.OVERVIEW, "Test")
        args.put(MainActivity.POSTER_PATH, "Test")

        val urxx = contentResolver.insert(CONTENT_URI_TV_SHOW, args)
        return urxx!!.pathSegments[1]
    }

    fun selectTVShow(contentResolver: ContentResolver):List<TvShow>{
        val cursor = contentResolver.query(CONTENT_URI_TV_SHOW, null, null, null, null)
        val listTvShow = mutableListOf<TvShow>()
        cursor.use {
            cursor.use { cursor ->
                do{
                    listTvShow.add(
                        TvShow(
                            cursor!!.getInt(cursor.getColumnIndex(_ID)),
                            cursor.getString(cursor.getColumnIndex(TITLE)),
                            cursor.getString(cursor.getColumnIndex(OVERVIEW)),
                            cursor.getString(cursor.getColumnIndex(POSTER_PATH)
                            )
                        ))
                }while (cursor!!.moveToNext())
            }
        }
        return listTvShow
    }
}
