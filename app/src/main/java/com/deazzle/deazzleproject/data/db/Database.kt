package com.deazzle.deazzleproject.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.deazzle.deazzleproject.data.db.entities.ProfilesModel

@androidx.room.Database(entities = [ProfilesModel::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun recommendedProfilesDao(): RecommendedProfilesDao

    companion object {
        @Volatile
        private var INSTANCE: Database? = null

        fun getDatabase(context: Context): Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "deazzle_project_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}