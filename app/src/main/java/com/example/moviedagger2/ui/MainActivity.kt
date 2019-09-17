package com.example.moviedagger2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviedagger2.R
import com.example.moviedagger2.adapter.MoviePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_view_pager.adapter = MoviePagerAdapter(this, supportFragmentManager)
        main_tab_layout.setupWithViewPager(main_view_pager)
    }
}
