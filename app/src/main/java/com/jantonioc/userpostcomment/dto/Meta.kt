package com.jantonioc.userpostcomment.dto

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("pagination") var pagination: Pagination
)