package com.deazzle.deazzleproject.data.network

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class RecommendedProfiles(

  @SerializedName("results")
    var results: ArrayList<Result> =  ArrayList()

)

data class Result(@Embedded val name: Name, @Embedded val picture: Picture, @Embedded val login: LoginDetails,@Embedded val dob:DOB)

data class Name(val first:String,val last:String,val title:String)
data class Picture(val large:String,val medium:String,val thumbnail:String)
data class LoginDetails(val uuid:String)
data class DOB(val age:Int)