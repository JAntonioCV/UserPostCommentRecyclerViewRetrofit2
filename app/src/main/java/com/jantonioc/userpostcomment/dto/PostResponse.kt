package com.jantonioc.userpostcomment.dto

import com.google.gson.annotations.SerializedName

data class PostResponse (
    @SerializedName("meta") var meta: Meta,
    @SerializedName("data") var data: List<Post>
)