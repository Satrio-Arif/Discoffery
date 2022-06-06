package com.project.discofferytemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.project.discofferytemp.adapter.OnboardingAdapter
import com.project.discofferytemp.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private var itemlist = ArrayList<OnboardingData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setUpPager()
    }

    private fun setUpPager() {
        itemlist = getItems()
        binding.viewPager.adapter = OnboardingAdapter(itemlist)
        binding.dotsIndicator.setViewPager2(binding.viewPager)
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private var pageChangeCallback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.imgPrevious.visibility =
                    View.INVISIBLE.takeIf { position == 0 } ?: View.VISIBLE

                if(position == itemlist.size - 1){
                    binding.tvNext.text = "Start"
                }else{
                    binding.tvNext.text ="Next"
                }
            }
        }

    private fun getItems(): ArrayList<OnboardingData> {
        val items = ArrayList<OnboardingData>()
        items.add(
            OnboardingData(
                R.drawable.ob1,
                R.drawable.h1,
                "Lorem ipsum dolor sit amet, consectetur  adipiscin adipiscing elit."
            )
        )
        items.add(
            OnboardingData(
                R.drawable.ob2,
                R.drawable.h2,
                "Lorem ipsum dolor sit amet, consectetur  adipiscin adipiscing elit."
            )
        )
        items.add(
            OnboardingData(
                R.drawable.ob3,
                R.drawable.h3,
                "Lorem ipsum dolor sit amet, consectetur  adipiscin adipiscing elit."
            )
        )
        items.add(
            OnboardingData(
                R.drawable.ob4,
                R.drawable.h4,
                "Lorem ipsum dolor sit amet, consectetur  adipiscin adipiscing elit."
            )
        )
        return items
    }


    fun onClick(view: View) {
        when (view) {
            binding.imgPrevious -> {
                val currentItemPosition = binding.viewPager.currentItem
                if(currentItemPosition == 0) return
                binding.viewPager.setCurrentItem(currentItemPosition-1,true)
            }
            binding.tvNext ->{
                val currentItemPosition = binding.viewPager.currentItem
                if (currentItemPosition == itemlist.size - 1){
                    intent = Intent(this@OnboardingActivity, LoginActivty::class.java)
                    intent.flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                binding.viewPager.setCurrentItem(currentItemPosition + 1,true)
            }
        }

    }
}