package com.rubahapi.themoviefavorite.tvshowfragment

import com.rubahapi.themoviefavorite.model.TvShow

interface TVShowView {
    fun onAttachView()
    fun onDetachView()
    fun showTVShow(data: List<TvShow>)
}