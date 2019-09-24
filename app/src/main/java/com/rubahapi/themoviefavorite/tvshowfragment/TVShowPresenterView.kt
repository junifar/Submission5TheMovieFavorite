package com.rubahapi.themoviefavorite.tvshowfragment

import androidx.fragment.app.Fragment

interface TVShowPresenterView<T: Fragment> {
    fun onAttach(view: T)
    fun onDetach()
}