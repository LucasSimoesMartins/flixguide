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

        v?.rvPopular?.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = popularListAdapter
        }

        viewModel.imgFeaturedMovie.observe(viewLifecycleOwner, Observer { featuredMovie ->
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${featuredMovie}")
                .into(v?.imgFeaturedEntertainment)
        })

        viewModel.imgPopularMovieList.observe(viewLifecycleOwner, Observer { imgPopularMovieList ->
            popularListAdapter.updateList(imgPopularMovieList)
        })

        viewModel.getMovies()
    }

    override fun onRefresh() {
        viewModel.getMovies()
        swipeRefreshLayout.isRefreshing = false
    }

}
