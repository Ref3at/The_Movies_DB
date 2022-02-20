package com.refaat.themoviesdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.refaat.themoviesdb.R
import com.refaat.themoviesdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}