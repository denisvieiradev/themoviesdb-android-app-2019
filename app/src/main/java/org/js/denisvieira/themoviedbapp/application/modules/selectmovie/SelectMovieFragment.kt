package org.js.denisvieira.themoviedbapp.application.modules.selectmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.js.denisvieira.themoviedbapp.R

class SelectMovieFragment : Fragment() {

    private lateinit var mBinding: org.js.denisvieira.themoviedbapp.databinding.SelectMovieFragmentBinding
    private lateinit var mViewModel: SelectMovieViewModel

    companion object {

        fun newInstance() = SelectMovieFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = inflate(inflater, R.layout.select_movie_fragment, container, false)

        mBinding.lifecycleOwner = this

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this)
            .get(SelectMovieViewModel::class.java)

    }


}