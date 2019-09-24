package com.rubahapi.themoviefavorite.moviefragment

import com.rubahapi.themoviefavorite.model.Movie

interface MovieView {
    fun onAttachView()
    fun onDetachView()
    fun showMovie(data: List<Movie>)
}