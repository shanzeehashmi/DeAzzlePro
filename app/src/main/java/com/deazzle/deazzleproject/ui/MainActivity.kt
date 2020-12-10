package com.deazzle.deazzleproject.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.deazzle.deazzleproject.MyApplication
import com.deazzle.deazzleproject.data.adapters.RecommendedProfileAdapter
import com.deazzle.deazzleproject.data.constants.Constant
import com.deazzle.deazzleproject.data.db.entities.ProfilesModel
import com.deazzle.deazzleproject.databinding.ActivityMainBinding
import com.yuyakaido.android.cardstackview.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


class MainActivity : AppCompatActivity() , CardStackListener,
    RecommendedProfileAdapter.OnRecyclerButtonClickedListener {

    private lateinit var tinderCard: CardStackView
    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter:RecommendedProfileAdapter

    private lateinit var profileList: List<ProfilesModel>

    private var count : Int = 0

    private val profileViewModel : ProfileViewModel by viewModels  {
        ViewModelFactory((application as MyApplication).repository)
    }

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecommendedProfileAdapter(this)

        val layoutManager = CardStackLayoutManager(this, this)
        tinderCard = binding.tinderCard
        tinderCard.layoutManager = layoutManager
        tinderCard.rewind()
        tinderCard.adapter = adapter
        tinderCard.itemAnimator = null


        profileViewModel.getProfiles().observe(this, {
            adapter.setProfiles(it)
            count = it.size
            profileList = it

            if(count!=0)  binding.progressCircular.visibility = View.GONE

        })


        profileViewModel.getIsLoading()?.observe(this,{
            if(it)
            {
                binding.progressCircular.visibility = View.VISIBLE
            }
            else
            {
                binding.progressCircular.visibility = View.GONE
            }
        })

    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {
        if(count-10 == position )
        {
            profileViewModel.getMoreProfiles()
        }

    }

    override fun onCardDisappeared(view: View?, position: Int) {
        if(position+1 == count) adapter.notifyDataSetChanged()
    }

    override fun onLikedButtonClicked(uuid: String, profile: ProfilesModel) {
       profileViewModel.markAsLiked(uuid)
        val position = profileList.indexOf(profile)
      try {
          profileList[position].liked = Constant.ACCEPTED
          adapter.notifyItemChanged(position)
      } catch (e: Exception)
      {
          // do nothing
      }
    }

    override fun onDisLikedButtonClicked(uuid: String, profile: ProfilesModel) {
       profileViewModel.markAsDisLiked(uuid)
        val position = profileList.indexOf(profile)
        try {
            profileList[position].liked = Constant.DECLINED
            adapter.notifyItemChanged(position)

        } catch (e: Exception)
        {
            // do nothing
        }
    }

}

