package com.andricohalim.freegames.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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


        val layoutManager = LinearLayoutManager(this)
        binding.rvTips.layoutManager = layoutManager

        mainViewModel.listGames.observe(this) { result ->
            when (result) {
                is Result.Loading -> {
//                    showLoading(true)
                }

                is Result.Success -> {
//                    showLoading(false)
                    setupAction(result.data)
                }

                is Result.Error -> {
//                    showLoading(false)
//                    binding.tvError.text = result.error
//                    binding.tvError.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupAction(storyList: List<GamesResponse>) {
        binding.apply {
            val adapter = GamesAdapter(storyList)
            binding.rvTips.adapter = adapter
        }
    }
}