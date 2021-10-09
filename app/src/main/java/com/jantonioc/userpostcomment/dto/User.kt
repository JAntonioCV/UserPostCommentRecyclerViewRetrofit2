package com.jantonioc.userpostcomment.dto

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("email") var email : String,
    @SerializedName("gender") var gender : String,
    @SerializedName("status") var status : String,
)
