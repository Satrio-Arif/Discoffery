package com.project.discofferytemp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.discofferytemp.OnboardingData
import com.project.discofferytemp.databinding.ItemOnboardingBinding

class OnboardingAdapter(private val items: ArrayList<OnboardingData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            showOne -> {
                TopViewHolder(
                    ItemOnboardingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                BottomViewHolder(
                    ItemOnboardingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = items[position]
        when(holder){
            is TopViewHolder -> holder.bind(data)
            is BottomViewHolder -> holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return showOne.takeIf { position % 2 == 0 } ?: showTwo
    }

    class TopViewHolder(private val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OnboardingData) {
            binding.imageOB.setImageResource(data.imageOB)
            binding.imageHeader.setImageResource(data.imageHeader)
            binding.tvDesc.text = data.desc
        }
    }

    class BottomViewHolder(private val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OnboardingData) {
            binding.imageOB.setImageResource(data.imageOB)
            binding.imageHeader.setImageResource(data.imageHeader)
            binding.tvDesc.text = data.desc
        }
    }


    companion object {
        const val showOne = 1
        const val showTwo = 2

    }


}