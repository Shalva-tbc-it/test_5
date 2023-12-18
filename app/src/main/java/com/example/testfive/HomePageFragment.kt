package com.example.testfive

import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testfive.adapter.HorizontalRecyclerAdapter
import com.example.testfive.adapter.VerticalRecyclerAdapter
import com.example.testfive.api.ApiViewModel
import com.example.testfive.api.CoursesState
import com.example.testfive.base.BaseFragment
import com.example.testfive.databinding.FragmentHomePageBinding
import kotlinx.coroutines.launch


class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate) {

    private val viewModel: ApiViewModel by viewModels()
    private lateinit var horizontalRecyclerAdapter: HorizontalRecyclerAdapter
    private lateinit var verticalRecyclerAdapter: VerticalRecyclerAdapter
    override fun start() {
        observe()
        setAdapter()
    }

    override fun clickListener() {

    }

    private fun setAdapter() {
        horizontalRecyclerAdapter = HorizontalRecyclerAdapter()
        binding.recyclerHorizontal.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerHorizontal.adapter = horizontalRecyclerAdapter

        verticalRecyclerAdapter = VerticalRecyclerAdapter()
        binding.recyclerVertical.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerVertical.adapter = verticalRecyclerAdapter
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.coursesState.collect { state ->
                when (state) {
                    is CoursesState.Loading -> {
                        // load indicator
                    }
                    is CoursesState.Success -> {
                        val coursesResponse = state.coursesResponse

                        if (coursesResponse != null) {
                            // update RecyclerView
                            horizontalRecyclerAdapter.submitList(coursesResponse.newCourses)
                            verticalRecyclerAdapter.submitList(coursesResponse.activeCourses)
                        } else {
                            // coursesResponse = null
                        }
                    }
                    is CoursesState.Error -> {
                        val errorMessage = state.errorMessage
                        Toast.makeText(requireContext(),"$errorMessage", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}