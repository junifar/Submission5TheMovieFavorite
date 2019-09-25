package com.rubahapi.themoviefavorite

import android.content.ContentValues
import android.content.UriMatcher
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.rubahapi.themoviefavorite.adapter.SectionsPagerAdapter
import com.rubahapi.themoviefavorite.model.TvShow
import com.rubahapi.themoviefavorite.resolver.TVShowResolver

class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.main_menu)

    }
}
