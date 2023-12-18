package com.example.testfive.api

import com.example.testfive.model.CoursesResponse

sealed class CoursesState {
    object Loading : CoursesState()
    data class Success(val coursesResponse: CoursesResponse?) : CoursesState()
    data class Error(val errorMessage: String) : CoursesState()
}