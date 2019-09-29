package com.rubahapi.themoviefavorite.tvshowfragment


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubahapi.moviecatalogue.adapter.TvShowAdapter
import com.rubahapi.themoviefavorite.DetailMovieActivity
import com.rubahapi.themoviefavorite.R
import com.rubahapi.themoviefavorite.adapter.MovieAdapter
import com.rubahapi.themoviefavorite.model.Movie
import com.rubahapi.themoviefavorite.model.TvShow
import com.rubahapi.themoviefavorite.resolver.TVShowResolver

class TvShowFragment(context: Context) : Fragment(), TVShowView {
    private lateinit var presenter: TVShowPresenter
    private lateinit var adapter: TvShowAdapter
    private lateinit var list:RecyclerView
    private var items: ArrayList<TvShow> = arrayListOf()
    private val contextMain = context
    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDetachView() {
        presenter.onDetach()
    }

    override fun showTVShow(data: List<TvShow>) {
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initComponent(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv_show, container, false)
        list = view.findViewById(R.id.recycler_view_tv_show)

        list.layoutManager = LinearLayoutManager(context)
//        list.adapter = TvShowAdapter(context, items){
//            val intent = Intent(activity, DetailMovieActivity::class.java)
//            intent.putExtra(DetailMovieActivity.EXTRA_DETAIL_ACTIVITY_TYPE, DetailMovieActivity.EXTRA_DETAIL_TV_SHOW)
//            intent.putExtra(DetailMovieActivity.EXTRA_DETAIL_TV_SHOW, it)
//            startActivity(intent)
//        }

        return view
    }

    private fun initComponent(savedInstanceState: Bundle?){
        adapter = TvShowAdapter(context!!, items){
            Toast.makeText(contextMain, it.name, Toast.LENGTH_LONG).show()
            val intent = Intent(activity, DetailMovieActivity::class.java)
            intent.putExtra(
                DetailMovieActivity.EXTRA_DETAIL_ACTIVITY_TYPE,
                DetailMovieActivity.EXTRA_DETAIL_TV_SHOW
            )
            intent.putExtra(DetailMovieActivity.EXTRA_DETAIL_TV_SHOW, it)
            startActivity(intent)
        }
        list.adapter = adapter

        val tvShowResolver = TVShowResolver()
        val result = tvShowResolver.selectTVShow(contextMain.contentResolver)
        showTVShow(result)
    }

    companion object{

        @JvmStatic
        fun newInstance(context: Context): TvShowFragment {
            return TvShowFragment(context).apply {
                arguments = Bundle().apply {}
            }
        }
    }

}
