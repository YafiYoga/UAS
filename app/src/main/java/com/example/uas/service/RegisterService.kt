package com.example.uas.service

import com.example.uas.respon.LoginRespon
import com.example.uas.data.RegisterData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("auth/local/register")
    fun saveData(@Body body: RegisterData) : Call<LoginRespon>
}