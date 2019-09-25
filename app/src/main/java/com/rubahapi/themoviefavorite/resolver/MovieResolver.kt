package com.rubahapi.themoviefavorite.resolver

import android.content.ContentResolver
import android.content.ContentValues
import android.content.UriMatcher
import android.net.Uri
import android.provider.BaseColumns
import com.rubahapi.themoviefavorite.model.Movie
import com.rubahapi.themoviefavorite.resolver.TVShowResolver.TvShowColumns.Companion.OVERVIEW
import com.rubahapi.themoviefavorite.resolver.TVShowResolver.TvShowColumns.Companion.POSTER_PATH
import com.rubahapi.themoviefavorite.resolver.TVShowResolver.TvShowColumns.Companion.TITLE
import com.rubahapi.themoviefavorite.resolver.TVShowResolver.TvShowColumns.Companion._ID

class MovieResolver{
    private val sURIMatcher = UriMatcher(UriMatcher.NO_MATCH)
    private val MOVIES = 1
    private val MOVIES_ID = 2

    companion object{
        const val AUTHORITY = "com.rubahapi.moviedb.provider.MovieProvider"
        const val TABLE_MOVIE = "movie_catalogue_db"
        val CONTENT_URI_MOVIE: Uri = Uri.parse("content://" + AUTHORITY + "/" +
                TABLE_MOVIE)
    }

    internal class MovieColumns : BaseColumns {
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
            TABLE_MOVIE, MOVIES)
        sURIMatcher.addURI(
            AUTHORITY, "${TABLE_MOVIE}/#",
            MOVIES_ID)
    }

    fun insertMovie(contentResolver:ContentResolver, movie: Movie):String {
        val args = ContentValues()
        args.put(_ID, movie.id)
        args.put(TITLE, movie.title)
        args.put(OVERVIEW, movie.overview)
        args.put(POSTER_PATH, movie.poster_path)

        val urxx = contentResolver.insert(CONTENT_URI_MOVIE, args)
        return urxx!!.pathSegments[1]
    }

    fun selectMovie(contentResolver: ContentResolver):ArrayList<Movie>{
        val cursor = contentResolver.query(CONTENT_URI_MOVIE, null, null, null, null)
        val listMovie = arrayListOf<Movie>()
        cursor.moveToFirst()
        if (cursor.count > 0){
            while (!cursor.isAfterLast){
                val movie = Movie(
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
}
