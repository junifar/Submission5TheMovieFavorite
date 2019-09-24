package com.rubahapi.themoviefavorite.moviefragment

import android.content.ContentValues.TAG
import android.util.Log
import androidx.fragment.app.Fragment
import com.rubahapi.themoviefavorite.model.Movie
import kotlinx.coroutines.CoroutineExceptionHandler

class MoviePresenter(private val view: MovieView): MoviePresenterView<MovieFragment> {
    private var mView: Fragment? = null

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, "$exception handled !")
        val listData = mutableListOf<Movie>()
        listData.add(Movie(0,"No Connection Detected", "",""))
        view.showMovie(listData)
    }

    override fun onAttach(view: MovieFragment) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

}