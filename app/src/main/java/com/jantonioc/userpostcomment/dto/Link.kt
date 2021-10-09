package com.jantonioc.userpostcomment.dto

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("previous") var previous:String,
    @SerializedName("current")  var current:String,
    @SerializedName("next")     var next: String
)
