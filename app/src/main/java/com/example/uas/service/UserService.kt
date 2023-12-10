package com.example.uas.service


import com.example.uas.data.UpdateData
import com.example.uas.respon.LoginRespon
import com.example.uas.respon.UserRespon
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("users")
    fun getData(): Call<List<UserRespon>>

    @DELETE("users/{id}")
    fun delete(@Path("id") id: Int): Call<UserRespon>

    @PUT("users/{id}")
    fun save(@Path("id") id: String?, @Body body: UpdateData): Call<LoginRespon>
}