package com.example.uas.service

import com.example.uas.data.LayananDataWrapper
import com.example.uas.respon.LayananRespon
import com.example.uas.respon.layanan
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LayananService {
    @POST("layanans")
    fun createLayanan(@Body layananData: LayananDataWrapper): Call<LayananRespon>

    @PUT("layanans/{id}")
    fun save(@Path("id") id: String?, @Body body: LayananDataWrapper): Call<LayananRespon>

    @GET("layanans")
    fun getData(): Call<layanan<List<LayananRespon>>>

    @DELETE("layanans/{id}")
    fun delete(@Path("id") id: Int): Call<LayananRespon>

}