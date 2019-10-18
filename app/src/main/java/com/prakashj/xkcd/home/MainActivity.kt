package com.prakashj.xkcd.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.prakashj.xkcd.infra.di.AppViewModelFactory
import com.prakashj.xkcd.R

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this,
            AppViewModelFactory()
        ).get(MainViewModel::class.java)
    }
}
