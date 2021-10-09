package com.jantonioc.userpostcomment.dto

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("id") var id : Int,
    @SerializedName("user_id") var user_id : Int,
    @SerializedName("title") var title : String,
    @SerializedName("body") var body : String
)