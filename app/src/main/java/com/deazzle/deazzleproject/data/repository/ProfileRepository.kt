package com.deazzle.deazzleproject.data.repository

import com.deazzle.deazzleproject.data.db.entities.ProfilesModel
import com.deazzle.deazzleproject.data.db.Database
import com.deazzle.deazzleproject.data.network.NetworkConnectionInterceptor
import com.deazzle.deazzleproject.data.network.UsersProfileApi
import com.deazzle.deazzleproject.utils.ShowToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import java.lang.Exception


class ProfileRepository(
    private val db : Database
){

    @ExperimentalCoroutinesApi
    private val isLoading: Flow<Boolean> = MutableStateFlow(true)

    fun getIsLoading():Flow<Boolean>?
    {
        return isLoading
    }


    @ExperimentalCoroutinesApi
    fun getProfilesFromRepo():Flow<List<ProfilesModel>>
    {
        isLoading as MutableStateFlow
        isLoading.value = true
        return db.recommendedProfilesDao().getProfilesRecommendationFromDatabase()
    }

    suspend fun markAsLiked(uuid:String)
    {
        withContext(Dispatchers.IO)
        {
            db.recommendedProfilesDao().markRecommendationAsLiked(uuid)
        }
    }

    suspend fun markAsDisLiked(uuid:String)
    {
        withContext(Dispatchers.IO)
        {
            db.recommendedProfilesDao().markRecommendationAsDisLiked(uuid)
        }
    }

    suspend fun populateDatabase(){
        withContext(Dispatchers.IO){
            try {
            val newProfiles = UsersProfileApi(NetworkConnectionInterceptor()).getUsers()

            val profilesArrayList : ArrayList<ProfilesModel> = ArrayList()

            if( newProfiles.isSuccessful && newProfiles.code() == 200)
            {
                val profileResponse = newProfiles.body() !!
                for( profile in profileResponse.results){
                    profilesArrayList.add(
                        ProfilesModel(
                            ""+profile.login.uuid,
                            ""+profile.name.first,
                            ""+profile.name.last,
                            ""+profile.name.title,
                            ""+profile.picture.large,
                            profile.dob.age.toString()
                    )
                    )
                }

                db.recommendedProfilesDao().storeRecommendationsInDatabase(profilesArrayList)
            }else
            {
                withContext(Dispatchers.Main)
                {
                    ShowToast("Could not sync new feed !!")
                    isLoading as MutableStateFlow
                    isLoading.value = false
                }
            }

            }catch (ex:Exception)
            {
                withContext(Dispatchers.Main)
                {
                    ShowToast("No Network !!")
                    isLoading as MutableStateFlow
                    isLoading.value = false
                }


            }

        }
    }



}