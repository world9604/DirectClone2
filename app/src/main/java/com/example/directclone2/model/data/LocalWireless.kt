package com.example.directclone2.model.data

import com.google.gson.annotations.SerializedName

data class LocalWireless(
    @SerializedName("bluetooth")
    val bluetooth: String = "ON",
    @SerializedName("nfc")
    val nfc: String = "ON",
    @SerializedName("gps")
    val useLocation: String = "OFF",
    @SerializedName("wlan")
    val wifi: String = "OFF",
    @SerializedName("saved_networks")
    val savedNetworks: List<String> = listOf(""),
    @SerializedName("roaming")
    val roaming: String = "",
    @SerializedName("vpn")
    val vpn: String = "",
    @SerializedName("data_server")
    val dataServer: String = "",
)