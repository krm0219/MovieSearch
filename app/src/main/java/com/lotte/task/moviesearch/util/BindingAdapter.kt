package com.lotte.task.moviesearch.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lotte.task.moviesearch.R
import com.lotte.task.moviesearch.main.MainAdapter
import com.lotte.task.moviesearch.model.MovieModel

object BindingAdapter {

    @BindingAdapter("movieData")
    @JvmStatic
    fun bindData(recyclerView: RecyclerView, repositories: List<MovieModel>?) {

        val adapter = recyclerView.adapter as MainAdapter
        adapter.submitList(repositories)
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?) {

        Glide.with(imageView.context).load(url)
            .error(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}