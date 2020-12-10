package com.deazzle.deazzleproject.data.network

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UsersProfileApi {

    @GET("api/?results=40")
    suspend fun  getUsers() : Response<RecommendedProfiles>


   companion object {

       operator fun invoke(
           networkConnectionInterceptor: NetworkConnectionInterceptor
       ): UsersProfileApi {

           val okHttpClient = OkHttpClient.Builder()
               .addInterceptor(networkConnectionInterceptor)
               .build()

           return Retrofit.Builder()
               .client(okHttpClient)
               .baseUrl("https://randomuser.me/")
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(UsersProfileApi::class.java)
       }
    }


}