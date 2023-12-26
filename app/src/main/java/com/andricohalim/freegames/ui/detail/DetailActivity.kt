package com.andricohalim.freegames.ui.detail

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat
import com.andricohalim.freegames.R
import com.andricohalim.freegames.databinding.ActivityDetailBinding
import com.andricohalim.freegames.databinding.ActivityMainBinding
import com.andricohalim.freegames.response.GamesResponse
import com.andricohalim.freegames.util.formatDate
import com.andricohalim.freegames.util.loadImage

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateCustomActionBarTitle("Giveaway Detail")

        setData()
    }

    private fun setData() {
        val games = IntentCompat.getParcelableExtra(intent, DETAIL_INFO, GamesResponse::class.java)
        binding.apply {
            loadImage(applicationContext, games!!.image, ivGames)
            tvTitle.text = games.title
            tvPlatform.text = getString(R.string.platformtxt, games.platforms)
            val formatEndDate = formatDate(games.endDate)
            tvEndDate.text = getString(R.string.giveaway_end, formatEndDate)
            tvDescription.text = games.description
            btnClaim.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@DetailActivity, R.color.black))
            btnClaim.setOnClickListener {
                openUrlInBrowser(games.openGiveawayUrl)
            }
            tvStepInstruction.text = games.instructions
        }
    }

    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share -> {
                shareGame()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareGame() {
        val games = IntentCompat.getParcelableExtra(intent, DETAIL_INFO, GamesResponse::class.java)

        val shareText = getString(
            R.string.share_game_text,
            games?.title,
            games?.openGiveawayUrl,
            games?.platforms
        )

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_game_subject))
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }


    fun updateCustomActionBarTitle(title: String) {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.customactionbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val customActionBarView = supportActionBar?.customView
        val titleTextView = customActionBarView?.findViewById<TextView>(R.id.tvTitle)
        titleTextView?.text = title
    }

    companion object {
        const val DETAIL_INFO = "detail_info"
    }
}