package com.example.uas.service

import com.example.uas.data.LayananDataWrapper
import com.example.uas.data.PemesananDataWrapper
import com.example.uas.respon.LayananRespon
import com.example.uas.respon.PemesananRespon
import com.example.uas.respon.layanan
import com.example.uas.respon.pemesanan
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PemesananService {
    @POST("pemesanans")
    fun createPemesanan(@Body pemesananData: PemesananDataWrapper): Call<PemesananRespon>

    @PUT("pemesanans/{id}")
    fun save(@Path("id") id: String?, @Body body: PemesananDataWrapper): Call<PemesananRespon>

    @GET("pemesanans")
    fun getData(): Call<pemesanan<List<PemesananRespon>>>

    @DELETE("pemesanans/{id}")
    fun delete(@Path("id") id: Int): Call<PemesananRespon>

}