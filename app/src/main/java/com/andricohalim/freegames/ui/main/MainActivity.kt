package com.andricohalim.freegames.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.andricohalim.freegames.R
import com.andricohalim.freegames.adapter.GamesAdapter
import com.andricohalim.freegames.databinding.ActivityMainBinding
import com.andricohalim.freegames.response.GamesResponse
import com.andricohalim.freegames.util.ViewModelFactory
import com.andricohalim.freegames.util.Result

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateCustomActionBarTitle("Free Games Info")

        val layoutManager = LinearLayoutManager(this)
        binding.rvGames.layoutManager = layoutManager

        mainViewModel.listGames.observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }

                is Result.Success -> {
                    showLoading(false)
                    setupAction(result.data)
                }

                is Result.Error -> {
                    showLoading(false)
//                    binding.tvError.text = result.error
//                    binding.tvError.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupAction(gameList: List<GamesResponse>) {
        binding.apply {
            val adapter = GamesAdapter(gameList)
            binding.rvGames.adapter = adapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    fun updateCustomActionBarTitle(title: String) {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.customactionbar)

        val customActionBarView = supportActionBar?.customView
        val titleTextView = customActionBarView?.findViewById<TextView>(R.id.tvTitle)
        titleTextView?.text = title
    }

}