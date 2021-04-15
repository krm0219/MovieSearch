package com.lotte.task.moviesearch.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lotte.task.moviesearch.R
import com.lotte.task.moviesearch.databinding.ItemMovieBinding
import com.lotte.task.moviesearch.model.MovieModel

class MainAdapter : ListAdapter<MovieModel, MainAdapter.ViewHolder>(RepoDiffUtil) {

    var movies: List<MovieModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(layoutInflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {

        return R.layout.item_movie
    }

    override fun getItemCount(): Int {

        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movies[position]
        holder.bind(movie)
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieModel) {

            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    companion object RepoDiffUtil : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {

            // 각 아이템들의 고유한 값을 비
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {

            return oldItem == newItem
        }
    }

    fun setData(movies: List<MovieModel>) {

        this.movies = movies
        notifyDataSetChanged()
    }
}