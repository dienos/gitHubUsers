package com.jth.github.user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jth.github.user.databinding.FragmentUserFavoriteListBinding
import com.jth.github.user.repo.MainRepository
import com.jth.github.user.usecase.MainActivityUseCase
import com.jth.github.user.viewmodel.MainViewModel

class UsersFavoriteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserFavoriteListBinding.inflate(inflater, container, false)
        binding.viewModel = activity?.run {
            MainViewModel(MainActivityUseCase(this),  MainRepository.getInstance())
        }

        return binding.root
    }
}