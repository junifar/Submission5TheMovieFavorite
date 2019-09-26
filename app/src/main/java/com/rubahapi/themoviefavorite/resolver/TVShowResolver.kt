package com.rubahapi.themoviefavorite.resolver

import android.content.ContentResolver
import android.content.ContentValues
import android.content.UriMatcher
import android.net.Uri
import android.provider.BaseColumns
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

    fun selectTvShow(contentResolver: ContentResolver, id: Int):ArrayList<TvShow>{
        val cursor = contentResolver.query(CONTENT_URI_TV_SHOW, null, "$_ID = $id", null, null)
        val listMovie = arrayListOf<TvShow>()
        if (cursor.count > 0){
            while (!cursor.isAfterLast){
                val movie = TvShow(
                    cursor.getString(cursor.getColumnIndex(_ID)).toInt(),
                    cursor.getString(cursor.getColumnIndex(TITLE)),
                    cursor.getString(cursor.getColumnIndex(OVERVIEW)),
                    cursor.getString(cursor.getColumnIndex(POSTER_PATH))
                )
                listMovie.add(movie)
                cursor.moveToNext()
            }
        }
        cursor.close()
        return listMovie
    }

    fun insertTvShow(contentResolver:ContentResolver, tvShow: TvShow):String {
        val args = ContentValues()
        args.put(_ID, tvShow.id)
        args.put(TITLE, tvShow.name)
        args.put(OVERVIEW, tvShow.overview)
        args.put(POSTER_PATH, tvShow.poster_path)

        val urxx = contentResolver.insert(CONTENT_URI_TV_SHOW, args)
        return urxx!!.pathSegments[1]
    }

    fun selectTVShow(contentResolver: ContentResolver):ArrayList<TvShow>{
        val cursor = contentResolver.query(CONTENT_URI_TV_SHOW, null, null, null, null)
        val listTvShow = arrayListOf<TvShow>()
        cursor.moveToFirst()
        if (cursor.count > 0){
            while (!cursor.isAfterLast){
                val tvShow = TvShow(
                    cursor.getString(cursor.getColumnIndex(_ID)).toInt(),
                    cursor.getString(cursor.getColumnIndex(TITLE)),
                    cursor.getString(cursor.getColumnIndex(OVERVIEW)),
                    cursor.getString(cursor.getColumnIndex(POSTER_PATH))
                )
                listTvShow.add(tvShow)
                cursor.moveToNext()
            }
        }
        cursor.close()
        return listTvShow
    }
}
