package com.rubahapi.themoviefavorite.moviefragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubahapi.themoviefavorite.DetailMovieActivity
import com.rubahapi.themoviefavorite.R
import com.rubahapi.themoviefavorite.adapter.MovieAdapter
import com.rubahapi.themoviefavorite.model.Movie
import com.rubahapi.themoviefavorite.resolver.MovieResolver

class MovieFragment(context: Context) : Fragment(), MovieView {
    private lateinit var presenter: MoviePresenter
    private var items: ArrayList<Movie> = arrayListOf()
    private lateinit var list:RecyclerView
    private val mContext = context

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDetachView() {
        presenter.onDetach()
    }

    override fun showMovie(data: List<Movie>) {
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private lateinit var adapter: MovieAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initComponent(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        list = view.findViewById(R.id.recycler_view_movie)

        list.layoutManager = LinearLayoutManager(context)

        return view

    }

    private fun getMovieDB():List<Movie>{
        val result = mutableListOf<Movie>()
        val movie = Movie(1, "x", "y", "")
        result.add(movie)
        return result
    }

    private fun initComponent(savedInstanceState: Bundle?){
        adapter = MovieAdapter(items){
            val intent = Intent(activity, DetailMovieActivity::class.java)
            intent.putExtra(
                DetailMovieActivity.EXTRA_DETAIL_ACTIVITY_TYPE,
                DetailMovieActivity.EXTRA_DETAIL_MOVIE
            )
            intent.putExtra(DetailMovieActivity.EXTRA_DETAIL_MOVIE, it)
            startActivity(intent)
        }
        list.adapter = adapter

        val movieResolver = MovieResolver()

        val result = movieResolver.selectMovie(mContext.contentResolver)
        showMovie(result)
    }

    companion object{
        @JvmStatic
        fun newInstance(context: Context): MovieFragment {
            return MovieFragment(context).apply {
                arguments = Bundle().apply {}
            }
        }
    }
}
