package com.jth.github.user.view

import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class UserPagerAdapter : FragmentPagerAdapter {
    private val list: ArrayList<Fragment> = ArrayList()

    constructor(fragmentManager: FragmentManager) : super(fragmentManager) {
        list.add(UsersFragment())
        list.add(UsersFavoriteFragment())
    }

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Fragment = list[position]
}