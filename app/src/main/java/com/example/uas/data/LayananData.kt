package com.example.uas.data


import com.google.gson.annotations.SerializedName

data class LayananDataWrapper(@SerializedName("data") val LayananData: LayananData)

data class LayananData(
    val NamaLayanan: String,
    val DeskripsiLayanan: String,
    val Harga: Int
)