package com.jth.github.user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jth.github.user.databinding.FragmentUserListBinding
import com.jth.github.user.repo.MainRepository
import com.jth.github.user.usecase.MainActivityUseCase
import com.jth.github.user.viewmodel.MainViewModel
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import android.R
import androidx.recyclerview.widget.RecyclerView



class UsersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        val binding = FragmentUserListBinding.inflate(inflater, container, false)

        binding.viewModel = activity?.run {
            MainViewModel(MainActivityUseCase(this),  MainRepository.getInstance())
        }

        binding.viewModel?.searchUser("jth")

        val adapter = UserListAdapter(binding.viewModel as MainViewModel)
        binding.userList.adapter = adapter

        (binding.viewModel as MainViewModel).getSearchItem()
            .observe(this, Observer { items ->
                if (items != null && items.isNotEmpty()) {
                    binding.userList.visibility = View.VISIBLE
                    binding.empty.visibility = View.GONE

                    adapter.submitList(null)
                    adapter.submitList(items)
                } else {
                    binding.userList.visibility = View.GONE
                    binding.empty.visibility = View.VISIBLE

                    adapter.submitList(null)
                }
            })

        return binding.root
    }
}