package com.example.recipeapp_w41

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("/recipes/")
    fun getRecipies(): Call<List<RecipeDetails.Datum>>

    @POST("/recipes/")
    fun addRecipie(@Body userData: RecipeDetails.Datum): Call<RecipeDetails>

}