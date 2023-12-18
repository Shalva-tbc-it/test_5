package com.example.testfive.api

import com.example.testfive.model.CoursesResponse
import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("83160a49-fe85-46ba-bcf8-3cf5aa09f92b")
    fun getCourses(): Call<CoursesResponse>
}