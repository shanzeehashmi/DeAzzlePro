package com.deazzle.deazzleproject.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "profiles_model")
data class ProfilesModel(
    @PrimaryKey
    val uuid: String,
    val firstName:String,
    val lastName:String,
    val title:String,
    val profileUrl:String,
    val dob:String,
    var liked:String= "NO CHOICE"
)