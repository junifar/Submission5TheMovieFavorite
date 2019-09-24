package com.rubahapi.themoviefavorite.tvshowfragment


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubahapi.moviecatalogue.adapter.TvShowAdapter
import com.rubahapi.themoviefavorite.R
import com.rubahapi.themoviefavorite.adapter.MovieAdapter
import com.rubahapi.themoviefavorite.model.Movie
import com.rubahapi.themoviefavorite.model.TvShow

class TvShowFragment : Fragment(), TVShowView {
    private lateinit var presenter: TVShowPresenter
    private lateinit var adapter: TvShowAdapter
    private lateinit var list:RecyclerView
    private var items: ArrayList<TvShow> = arrayListOf()

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
            //            val intent = Intent(activity, DetailMovieActivity::class.java)
//            intent.putExtra(
//                DetailMovieActivity.EXTRA_DETAIL_ACTIVITY_TYPE,
//                DetailMovieActivity.EXTRA_DETAIL_MOVIE
//            )
//            intent.putExtra(DetailMovieActivity.EXTRA_DETAIL_MOVIE, it)
//            startActivity(intent)
        }
        list.adapter = adapter

        val result = getTVShowDB()
        showTVShow(result)
        val urxx = activity?.contentResolver?.query(CONTENT_URI_TV_SHOW, null, null, null, null)

    }

    private fun getTVShowDB():List<TvShow>{
        val result = mutableListOf<TvShow>()
        val tvshow = TvShow(1, "xShow", "yShow", "")
        result.add(tvshow)
        val x = activity?.contentResolver?.query(CONTENT_URI_TV_SHOW, null, null, null, null)
        x.use {

                do{
                    result.add(
                        TvShow(
                            x!!.getInt(x.getColumnIndex(_ID)),
                            x.getString(x.getColumnIndex(TITLE)),
                            x.getString(x.getColumnIndex(OVERVIEW)),
                            x.getString(x.getColumnIndex(POSTER_PATH)
                            )
                        ))
                }while (x!!.moveToNext())

        }
        return result
    }

    companion object{
        const val AUTHORITY = "com.rubahapi.moviedb.provider.TVShowProvider"
        const val TABLE_TV_SHOW = "tv_show_db"
        val _ID = "ID"
        val TITLE = "title"
        val OVERVIEW = "overview"
        val POSTER_PATH = "poster_path"
        val CONTENT_URI_TV_SHOW: Uri = Uri.parse("content://" + AUTHORITY + "/" +
                TABLE_TV_SHOW)

        @JvmStatic
        fun newInstance(): TvShowFragment {
            return TvShowFragment().apply {
                arguments = Bundle().apply {}
            }
        }
    }

}
