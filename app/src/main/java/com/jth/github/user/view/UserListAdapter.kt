package com.jth.github.user.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jth.github.user.BR
import com.jth.github.user.R
import com.jth.github.user.databinding.ItemGitHubUserListBinding
import com.jth.github.user.repo.model.User
import com.jth.github.user.viewmodel.MainViewModel


class UserListAdapter(val viewModel : MainViewModel) : PagedListAdapter<User, UserListAdapter.UserListViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val item = getItem(position)
        item?.apply {
            holder.bind(viewModel, this)
        }
    }

    override fun getItemCount() = super.getItemCount()

    override fun getItem(position: Int): User? = super.getItem(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_git_hub_user_list, parent, false)
        return  UserListViewHolder(view)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean
                    = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
        }
    }

    class UserListViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {

        var user : User? = null

        fun bind(viewModel: MainViewModel, user : User?) {
            val binding : ItemGitHubUserListBinding = DataBindingUtil.bind(view)!!
            val item =  user

            binding.viewModel = viewModel

            item?.apply {
                binding.setVariable(BR.user, this)
            }
        }
    }
}