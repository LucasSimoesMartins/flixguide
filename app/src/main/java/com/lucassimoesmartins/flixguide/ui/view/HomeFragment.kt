package com.lucassimoesmartins.flixguide.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lucassimoesmartins.flixguide.R
import com.lucassimoesmartins.flixguide.predefined.CategoryEnum
import com.lucassimoesmartins.flixguide.ui.view.adapter.EntertainmentListAdapter
import com.lucassimoesmartins.flixguide.ui.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: HomeViewModel by viewModel()
    private val popularListAdapter: EntertainmentListAdapter by inject()
    private val topRatedListAdapter: EntertainmentListAdapter by inject()
    private val topTenTodayListAdapter: EntertainmentListAdapter by inject()
    private val nowPlayingListAdapter: EntertainmentListAdapter by inject()
    private val upcomingListAdapter: EntertainmentListAdapter by inject()
    private val oldListAdapter: EntertainmentListAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        setUI(view)
        return view
    }

    private fun setUI(v: View?) {

        v?.swipeRefreshLayout?.let {
            it.setOnRefreshListener(this)
            it.swipeRefreshLayout?.setDistanceToTriggerSync(500)
        }

        setRV(v)

        setObserver(v)

        viewModel.getMovies()
    }

    private fun setRV(v: View?) {
        v?.rvPopular?.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = popularListAdapter
        }

        v?.rvTopRated?.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = topRatedListAdapter
        }

        v?.rvTopTenToday?.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = topTenTodayListAdapter
        }

        v?.rvNowPlaying?.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = nowPlayingListAdapter
        }

        v?.rvUpcoming?.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = upcomingListAdapter
        }

        v?.rvOld?.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = oldListAdapter
        }
    }

    private fun setObserver(v: View?) {

        viewModel.imgFeaturedMovie.observe(viewLifecycleOwner, Observer { featuredMovie ->
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${featuredMovie}")
                .into(v?.imgFeaturedEntertainment)
        })

        updateAdapter(CategoryEnum.POPULAR, popularListAdapter)
        updateAdapter(CategoryEnum.TOP_RATED, topRatedListAdapter)
        updateAdapter(CategoryEnum.TOP_10_TODAY, topTenTodayListAdapter)
        updateAdapter(CategoryEnum.NOW_PLAYING, nowPlayingListAdapter)
        updateAdapter(CategoryEnum.UPCOMING, upcomingListAdapter)
        updateAdapter(CategoryEnum.OLD, oldListAdapter)
    }

    private fun updateAdapter(categoryEnum: CategoryEnum, entertainmentListAdapter: EntertainmentListAdapter) {
        viewModel.getImgMovieList(categoryEnum).observe(viewLifecycleOwner, Observer { imgMovieList ->
            entertainmentListAdapter.updateList(imgMovieList)
        })
    }

    override fun onRefresh() {
        viewModel.getMovies()
        swipeRefreshLayout.isRefreshing = false
    }

}
