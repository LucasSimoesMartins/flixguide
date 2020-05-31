package com.lucassimoesmartins.flixguide.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lucassimoesmartins.flixguide.R
import com.lucassimoesmartins.flixguide.repository.MovieRepository
import com.lucassimoesmartins.flixguide.ui.viewmodel.HomeViewModel
import com.lucassimoesmartins.flixguide.ui.viewmodel.factory.HomeViewModelFactory
import kotlinx.android.synthetic.main.home_fragment.view.*


class HomeFragment : Fragment(), View.OnClickListener {

    private val viewModel by lazy {
        val repository = MovieRepository()
        val factory = HomeViewModelFactory(repository)
        ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        setUI(view)
        return view
    }

    private fun setUI(v: View?) {
        v?.txtMovies?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txtMovies -> {
                viewModel.getPopularMovies().observe(this, Observer { resource ->

                    resource.data?.let {
                        Toast.makeText(activity?.applicationContext, it.toString(), Toast.LENGTH_LONG).show()
                    }

                    resource.error?.let { errorMessage ->
                        Toast.makeText(activity?.applicationContext, errorMessage, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}
