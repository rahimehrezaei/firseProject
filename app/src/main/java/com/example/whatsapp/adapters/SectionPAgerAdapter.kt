package com.example.whatsapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.whatsapp.fragments.ChatsFragments
import com.example.whatsapp.fragments.UsersFragments

class SectionPagerAdapter (fm:FragmentManager) : FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return UsersFragments()
            1 -> return ChatsFragments()
        }
        return null!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "users"
            1 -> return "chats"
        }
        return null
    }
}