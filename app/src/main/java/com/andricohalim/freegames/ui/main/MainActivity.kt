package com.andricohalim.freegames.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.andricohalim.freegames.R
import com.andricohalim.freegames.adapter.GamesAdapter
import com.andricohalim.freegames.databinding.ActivityMainBinding
import com.andricohalim.freegames.response.GamesResponse
import com.andricohalim.freegames.util.Result
import com.andricohalim.freegames.util.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateCustomActionBarTitle(getString(R.string.free_games_info))

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
                    binding.tvError.text = result.error
                    binding.tvError.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.info -> {
                val toast = Toast.makeText(this,
                    getString(R.string.thanks_to_gamerpower_for_provide_api), Toast.LENGTH_SHORT)
                toast.show()
                true
            }

            else -> super.onOptionsItemSelected(item)
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

    private fun updateCustomActionBarTitle(title: String) {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.customactionbar)

        val customActionBarView = supportActionBar?.customView
        val titleTextView = customActionBarView?.findViewById<TextView>(R.id.tvTitle)
        titleTextView?.text = title
    }

}