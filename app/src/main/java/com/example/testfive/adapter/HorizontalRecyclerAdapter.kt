package com.example.testfive.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testfive.databinding.HorizontalRecyclerBinding
import com.example.testfive.model.Course

class HorizontalRecyclerAdapter():
    ListAdapter<Course, HorizontalRecyclerAdapter.HorizontalViewHolder>(object :
    DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }

    }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        return HorizontalViewHolder(
            HorizontalRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        holder.bind()
    }

    inner class HorizontalViewHolder(private val binding: HorizontalRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {

            val item = currentList[adapterPosition]
            tvIntroduce.text = item.iconType
            tvNews.text = item.question
            tvTime.text = item.duration

            val gradientDrawable = GradientDrawable()
            gradientDrawable.shape = GradientDrawable.RECTANGLE
            gradientDrawable.cornerRadii = floatArrayOf(20f, 20f, 20f, 20f, 20f, 20f, 20f, 20f)
            gradientDrawable.setColor(Color.parseColor("#" + item.mainColor))

            horizontalLinear.background = gradientDrawable
        }
    }

}