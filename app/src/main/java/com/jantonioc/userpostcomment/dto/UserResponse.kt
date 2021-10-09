package com.jantonioc.userpostcomment.dto

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("meta") var meta: Meta,
    @SerializedName("data") var data: List<User>
)
