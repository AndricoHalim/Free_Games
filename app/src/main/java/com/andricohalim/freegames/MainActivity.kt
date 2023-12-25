package com.andricohalim.freegames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
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

        mainViewModel.listStory.observe(this) { result ->
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


    override fun onResume() {
        super.onResume()
        // Tarik kembali data saat activity aktif kembali
        mainViewModel.getListStory()
    }




    private fun setupAction(storyList: List<GamesResponse>) {
        binding.apply {
            val adapter = GamesAdapter(storyList)
            binding.rvTips.adapter = adapter
        }
    }
}