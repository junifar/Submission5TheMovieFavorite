package com.rubahapi.themoviefavorite.moviefragment

import androidx.fragment.app.Fragment

interface MoviePresenterView<T: Fragment> {
    fun onAttach(view: T)
    fun onDetach()
}