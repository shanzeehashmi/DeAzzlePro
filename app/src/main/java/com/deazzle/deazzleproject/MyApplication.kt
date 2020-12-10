package com.deazzle.deazzleproject

import android.app.Application
import android.content.Context
import com.deazzle.deazzleproject.data.db.Database
import com.deazzle.deazzleproject.data.repository.ProfileRepository

class MyApplication : Application(){

    val database by lazy { Database.getDatabase(this) }
    val repository by lazy { ProfileRepository(database) }

    companion object {
        lateinit var  myAppContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        myAppContext = applicationContext
    }

}