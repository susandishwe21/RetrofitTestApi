package com.example.retrofttestapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_posts.*

class DetailPostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_posts)

        val title = intent.getStringExtra("TITLE")
        txtTitle.text = title
    }
}
