package com.deazzle.deazzleproject.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deazzle.deazzleproject.data.db.entities.ProfilesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RecommendedProfilesDao {

    @Query("SELECT * FROM profiles_model ")
    fun getProfilesRecommendationFromDatabase() : Flow<List<ProfilesModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun storeRecommendationsInDatabase(recommendedProfiles: List<ProfilesModel>)

    @Query("UPDATE profiles_model SET liked = 'ACCEPTED' WHERE uuid = :uuid")
    fun markRecommendationAsLiked(uuid:String)

    @Query("UPDATE profiles_model SET liked = 'DECLINED' WHERE uuid = :uuid")
    fun markRecommendationAsDisLiked(uuid:String)

}