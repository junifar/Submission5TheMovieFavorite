package com.rubahapi.themoviefavorite.tvshowfragment

import android.content.ContentValues.TAG
import android.util.Log
import androidx.fragment.app.Fragment
import com.rubahapi.themoviefavorite.model.Movie
import com.rubahapi.themoviefavorite.model.TvShow
import kotlinx.coroutines.CoroutineExceptionHandler

class TVShowPresenter(private val view: TVShowView): TVShowPresenterView<TvShowFragment> {
    private var mView: Fragment? = null

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, "$exception handled !")
        val listData = mutableListOf<TvShow>()
        listData.add(TvShow(0,"No Connection Detected", "",""))
        view.showTVShow(listData)
    }

    override fun onAttach(view: TvShowFragment) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

}