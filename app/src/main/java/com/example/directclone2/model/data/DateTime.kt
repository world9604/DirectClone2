package com.example.directclone2.model.data

import com.google.gson.annotations.SerializedName

data class DateTime (
    @SerializedName("use_network_provided_time")
    val useNetworkProvidedTime: String = "",
    @SerializedName("date_time_value")
    val systemDate: String = "",
    @SerializedName("time_value")
    val systemTime: String = "",
    @SerializedName("use_network_provided_timezone")
    val useNetworkProvidedTimeZone: String = "",
    @SerializedName("timezone")
    val timeZone: String = "",
    @SerializedName("use_24hour_format")
    val use24hourFormat: String = "",
)