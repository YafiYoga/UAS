package com.example.uas.respon


import com.google.gson.annotations.SerializedName

class LayananRespon {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("attributes")
    var attributes: LayananAttributes = LayananAttributes()
}

class LayananAttributes {
    @SerializedName("NamaLayanan")
    var NamaLayanan: String = ""
    @SerializedName("DeskripsiLayanan")
    var DeskripsiLayanan: String = ""
    @SerializedName("Harga")
    var Harga: Int = 0
    @SerializedName("createdAt")
    var createdAt: String = ""
    @SerializedName("updatedAt")
    var updatedAt: String = ""
}

data class layanan<T>(
    @SerializedName("data")
    val data: T?
)