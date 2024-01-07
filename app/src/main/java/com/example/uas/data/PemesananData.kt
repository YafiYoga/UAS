package com.example.uas.data



import com.google.gson.annotations.SerializedName
import java.util.Date

data class PemesananDataWrapper(@SerializedName("data") val PemesananData: PemesananData)

data class PemesananData(
    val NamaPemesan: String,
    val TglPemesanan: String,
    val JamPemesanan: String,
    val layanan: Int


)