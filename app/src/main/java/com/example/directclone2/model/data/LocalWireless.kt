package com.example.directclone2.model.data

import com.google.gson.annotations.SerializedName

data class LocalWireless(
    @SerializedName("bluetooth")
    var bluetooth: String = "ON",
    @SerializedName("nfc")
    var nfc: String = "ON",
    @SerializedName("gps")
    val useLocation: String = "OFF",
    @SerializedName("wlan")
    val wifi: String = "OFF",
    @SerializedName("saved_networks")
    val savedNetworks: List<String> = listOf(""),
    @SerializedName("data_sever")
    val dataSever: String = "",
    @SerializedName("roaming")
    val roaming: String = "",
    @SerializedName("vpn")
    val vpn: String = "",
)