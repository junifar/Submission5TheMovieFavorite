package com.rubahapi.themoviefavorite.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rubahapi.themoviefavorite.moviefragment.MovieFragment
import com.rubahapi.themoviefavorite.tvshowfragment.TvShowFragment
import com.rubahapi.themoviefavorite.R

private val TAB_TITLES = arrayOf(
    R.string.tab_movie,
    R.string.tab_tv_show
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var positionState = 0

    override fun getItem(position: Int): Fragment {
        this.positionState = position
        return when (position) {
            0 -> MovieFragment.newInstance(context)
            else -> TvShowFragment.newInstance(context)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}