package org.js.denisvieira.themoviedbapp.application.modules.selectmovie.viewholders

import androidx.recyclerview.widget.RecyclerView
import org.js.denisvieira.themoviedbapp.databinding.LoadingIconItemViewHolderBinding

class LoadingIconItemViewHolder(private val mLoadingIconItemViewHolderBinding: LoadingIconItemViewHolderBinding) :
        RecyclerView.ViewHolder(mLoadingIconItemViewHolderBinding.root) {

    fun showProgressBar() {
        mLoadingIconItemViewHolderBinding.progressBar.show()
        mLoadingIconItemViewHolderBinding.executePendingBindings()
    }

}