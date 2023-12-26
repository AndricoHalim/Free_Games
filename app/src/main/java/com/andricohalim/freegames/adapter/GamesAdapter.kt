package com.andricohalim.freegames.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.andricohalim.freegames.databinding.GamesItemBinding
import com.andricohalim.freegames.response.GamesResponse
import com.andricohalim.freegames.ui.detail.DetailActivity
//import com.andricohalim.freegames.response.GamesResponseItem
import com.andricohalim.freegames.util.loadImage

class GamesAdapter(private val listStory: List<GamesResponse>) :
    RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GamesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }

    class ViewHolder(private var binding: GamesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(games: GamesResponse) {
            loadImage(binding.root.context, games.image, binding.ivTips)
            binding.tvYourActivity.text = games.title

            binding.root.setOnClickListener {
                val detailIntent = Intent(binding.root.context, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.DETAIL_INFO, games)
                itemView.context.startActivity(detailIntent, ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity).toBundle())
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("GamesAdapter", "ItemCount: ${listStory.size}")
        return listStory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(listStory[position])
    }
}