package com.example.uas.respon


import com.google.gson.annotations.SerializedName
import retrofit2.Call

class PemesananRespon {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("attributes")
    var attributes: PemesananAttributes = PemesananAttributes()
}

class PemesananAttributes {
    @SerializedName("NamaPemesan")
    var NamaPemesanan: String = ""
    @SerializedName("TglPemesanan")
    var TglPemesanan: String = ""
    @SerializedName("JamPemesanan")
    var JamPemesanan: String = ""
    @SerializedName("layanan")
    val layanan: layanan<LayananRespon>? = null

    @SerializedName("createdAt")
    var createdAt: String = ""
    @SerializedName("updatedAt")
    var updatedAt: String = ""
}

data class pemesanan<T>(
    @SerializedName("data")
    val data: T?
)