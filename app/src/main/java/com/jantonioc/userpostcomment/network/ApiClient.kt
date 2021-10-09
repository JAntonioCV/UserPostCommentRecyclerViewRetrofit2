package com.jantonioc.userpostcomment.network

import com.jantonioc.userpostcomment.dto.PostResponse
import com.jantonioc.userpostcomment.dto.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {
    //obtiene toda la lista de usuarios
    @GET("/public/v1/users")
    suspend fun getListOfUser(): UserResponse

    //obtener los post de un usuario dado un id
    @GET("/public/v1/users/{param}/posts")
    suspend fun getPostUser(@Path("param") param:Int): PostResponse

}