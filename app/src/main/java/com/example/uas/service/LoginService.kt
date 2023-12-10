package com.example.uas.service

import com.example.uas.data.LoginData
import com.example.uas.respon.LoginRespon
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("auth/local")
    fun getData(@Body body: LoginData) : Call<LoginRespon>
}