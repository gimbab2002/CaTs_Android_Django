package com.example.myapplication.API
import android.media.MediaRouter.UserRouteInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register/")
    fun registerUser(@Body userRequest: UserRequest): Call<UserResponse>

    @POST("login/")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
}