package com.example.uas.respon


import com.google.gson.annotations.SerializedName

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
    @SerializedName("layanan")
    var pemesanan: pemesanan<PemesananRespon>? = null
    @SerializedName("JamPemesanan")
    var JamPemesanan: Int = 0
    @SerializedName("createdAt")
    var createdAt: String = ""
    @SerializedName("updatedAt")
    var updatedAt: String = ""
}

data class pemesanan<T>(
    @SerializedName("data")
    val data: T?
)