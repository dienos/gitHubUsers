package com.jth.github.user.view

import android.os.Bundle
import com.jth.github.user.R
import com.jth.github.user.binding.BaseBindingActivity
import com.jth.github.user.databinding.ActivityMainBinding
import com.jth.github.user.repo.MainRepository
import com.jth.github.user.usecase.MainActivityUseCase
import com.jth.github.user.viewmodel.MainViewModel

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    override fun getLayoutResId() : Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = MainRepository.getInstance()
        binding.lifecycleOwner = this
        binding.viewModel = MainViewModel(MainActivityUseCase(this), repo)

        binding.tabs.addTab(binding.tabs.newTab().setText("유저"))
        binding.tabs.addTab(binding.tabs.newTab().setText("즐겨찾기"))

        val adapter = UserPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}
