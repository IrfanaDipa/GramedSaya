package com.example.gramedsaya.ui.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.example.gramedsaya.R
import com.example.gramedsaya.adapter.SharedPreferencesHelper

class DetailActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_IMAGE = "extra_image"
        private const val EXTRA_TITLE = "extra_title"
        private const val EXTRA_DESCRIPTION = "extra_description"
        private const val EXTRA_SYNOPSIS = "extra_synopsis"

        fun newIntent(context: Context, imageResId: Int, title: String, description: String, synopsis: String): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_IMAGE, imageResId)
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESCRIPTION, description)
                putExtra(EXTRA_SYNOPSIS, synopsis)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Find views by their IDs
        val detailTitle: TextView = findViewById(R.id.detail_title)
        val detailDescription: TextView = findViewById(R.id.detail_description)
        val detailSynopsis: TextView = findViewById(R.id.detail_sinopsis)
        val detailImage: ImageView = findViewById(R.id.detail_image)
        val toggleStatus: ToggleButton = findViewById(R.id.toggle_status)

        // Get data from intent
        val title = intent.getStringExtra(EXTRA_TITLE)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)
        val synopsis = intent.getStringExtra(EXTRA_SYNOPSIS)
        val imageResId = intent.getIntExtra(EXTRA_IMAGE, -1)

        // Set data to views
        detailTitle.text = title
        detailDescription.text = description
        detailSynopsis.text = synopsis
        if (imageResId != -1) {
            detailImage.setImageResource(imageResId)
        }

        // Verify if the user is an admin
        if (SharedPreferencesHelper.isAdmin(this)) {
            val status = title?.let { SharedPreferencesHelper.getStatus(this, it) }
            toggleStatus.isChecked = (status == "Tersedia")

            toggleStatus.setOnCheckedChangeListener { _, isChecked ->
                val newStatus = if (isChecked) "Tersedia" else "Dipinjam"
                if (title != null) {
                    SharedPreferencesHelper.setStatus(this, title, newStatus)
                }
            }
        } else {
            toggleStatus.visibility = View.GONE
        }

        // Mengatur onClick listener untuk tombol "Back"
        val backButton: Button = findViewById(R.id.detail_button)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}