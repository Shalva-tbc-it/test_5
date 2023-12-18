package com.example.testfive.api

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testfive.model.CoursesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiViewModel : ViewModel() {

    private val _coursesState = MutableStateFlow<CoursesState>(CoursesState.Loading)
    val coursesState: StateFlow<CoursesState> get() = _coursesState

    init {
        fetchData()
    }

    private fun fetchData() {
        val apiService = RetrofitClient.apiService
        val call = apiService.getCourses()

        viewModelScope.launch {
            call.enqueue(object : Callback<CoursesResponse> {
                override fun onResponse(
                    call: Call<CoursesResponse>,
                    response: Response<CoursesResponse>
                ) {
                    if (response.isSuccessful) {
                        _coursesState.value = CoursesState.Success(response.body())
                    } else {
                        _coursesState.value = CoursesState.Error("Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<CoursesResponse>, t: Throwable) {
                    _coursesState.value = CoursesState.Error("Network request failed: ${t.message}")
                    Log.e("NetworkError", "Network request failed", t)
                }
            })
        }
    }
}
