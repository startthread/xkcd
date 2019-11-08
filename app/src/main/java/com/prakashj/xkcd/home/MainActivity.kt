package com.prakashj.xkcd.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.prakashj.xkcd.databinding.ActivityMainBinding
import com.prakashj.xkcd.infra.di.AppViewModelFactory
import com.prakashj.xkcd.infra.network.Comic

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, AppViewModelFactory()).get(MainViewModel::class.java)

        setupLiveData(viewModel)
    }

    private fun setupLiveData(viewModel: MainViewModel) {
        viewModel.comicLiveData.observe(this, Observer<Comic> { comic: Comic ->
            binding.titleTextView.text = comic.title
            binding.alternateTextView.text = comic.alt
        })
    }
}
