package com.jantonioc.userpostcomment.dto

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("total") var total: Int,
    @SerializedName("pages") var Integer:Int,
    @SerializedName("page")  var page: Int,
    @SerializedName("limit") var limit: Int,
    @SerializedName("links") var links: Link
)
