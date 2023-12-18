package com.example.testfive.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testfive.R
import com.example.testfive.databinding.VerticalRecyclerBinding
import com.example.testfive.model.ActiveCourse
import kotlinx.coroutines.GlobalScope

class VerticalRecyclerAdapter(): ListAdapter<ActiveCourse, VerticalRecyclerAdapter.VerticalViewHolder>( object :

    DiffUtil.ItemCallback<ActiveCourse>() {
    override fun areItemsTheSame(oldItem: ActiveCourse, newItem: ActiveCourse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ActiveCourse, newItem: ActiveCourse): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        return VerticalViewHolder(
            VerticalRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        holder.bind()
    }

    inner class VerticalViewHolder(private val binding: VerticalRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val item = currentList[adapterPosition]

            tvFirst.text = item.title
            root.setBackgroundColor(Color.parseColor("#" + item.mainColor))

            Glide.with(binding.root.context)
                .load(item.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(imgIcon)

            val gradientDrawable = GradientDrawable()
            gradientDrawable.shape = GradientDrawable.RECTANGLE
            gradientDrawable.cornerRadii = floatArrayOf(20f, 20f, 20f, 20f, 20f, 20f, 20f, 20f)
            gradientDrawable.setColor(Color.parseColor("#" + item.mainColor))

            verticalLinear.background = gradientDrawable

        }
    }

}