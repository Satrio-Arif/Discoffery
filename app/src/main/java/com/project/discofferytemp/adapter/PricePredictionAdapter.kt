package com.project.discofferytemp.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.discofferytemp.fragment.FirstPriceFragment
import com.project.discofferytemp.fragment.SecondPriceFragment

class PricePredictionAdapter(activity: AppCompatActivity):FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FirstPriceFragment()
            1 -> fragment = SecondPriceFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}