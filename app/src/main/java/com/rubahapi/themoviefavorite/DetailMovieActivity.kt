package com.rubahapi.themoviefavorite

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rubahapi.themoviefavorite.model.Movie
import com.rubahapi.themoviefavorite.model.TvShow
import com.rubahapi.themoviefavorite.resolver.MovieResolver
import com.rubahapi.themoviefavorite.resolver.TVShowResolver

class DetailMovieActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DETAIL_MOVIE = "DetailMovies"
        const val EXTRA_DETAIL_TV_SHOW = "DetailTVShow"
        const val EXTRA_DETAIL_ACTIVITY_TYPE = "DetailActovotyType"
    }

    private lateinit var movie: Movie
    private lateinit var tvShow: TvShow
    private lateinit var movieResolver: MovieResolver
    private lateinit var tvShowResolver: TVShowResolver
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
//    lateinit var database:MovieHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        when (intent.getStringExtra(EXTRA_DETAIL_ACTIVITY_TYPE)){
            EXTRA_DETAIL_MOVIE -> this.detailMovieInit()
            else -> this.detailTvShowInit()
        }

    }

    private fun getTvShowDBByID(id: Int):List<TvShow>{
        return tvShowResolver.selectTvShow(applicationContext.contentResolver,id)
    }

    private fun getMovieDBByID(id: Int):List<Movie>{
        return movieResolver.selectMovie(applicationContext.contentResolver, id)
    }

    private fun detailTvShowInit(){
        tvShow = intent.getParcelableExtra(EXTRA_DETAIL_TV_SHOW)

        tvShowResolver = TVShowResolver()
        getTvShowDBByID(tvShow.id)

        val imageLogo = findViewById<ImageView>(R.id.image_logo)
        val textMovieName = findViewById<TextView>(R.id.textMovieName)
        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)

        Glide.with(this).load("https://image.tmdb.org/t/p/w370_and_h556_bestv2${tvShow.poster_path}")
            .into(imageLogo)
        textMovieName.text = tvShow.name
        textViewDescription.text = tvShow.overview
    }

    private fun detailMovieInit(){
        movie = intent.getParcelableExtra(EXTRA_DETAIL_MOVIE)
        movieResolver = MovieResolver()
        getMovieDBByID(movie.id)

        val imageLogo = findViewById<ImageView>(R.id.image_logo)
        val textMovieName = findViewById<TextView>(R.id.textMovieName)
        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)

        Glide.with(this).load("https://image.tmdb.org/t/p/w370_and_h556_bestv2${movie.poster_path}")
            .into(imageLogo)
        textMovieName.text = movie.title
        textViewDescription.text = movie.overview
    }
}
