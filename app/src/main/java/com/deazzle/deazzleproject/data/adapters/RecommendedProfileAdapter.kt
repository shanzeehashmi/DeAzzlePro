package com.deazzle.deazzleproject.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.deazzle.deazzleproject.R
import com.deazzle.deazzleproject.data.db.entities.ProfilesModel
import com.deazzle.deazzleproject.databinding.LayoutTinderCardBinding

class RecommendedProfileAdapter(
    private val mListener:OnRecyclerButtonClickedListener
    ) : RecyclerView.Adapter<RecommendedProfileAdapter.ProfileViewHolder>()  {

    private var profiles: List<ProfilesModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ProfileViewHolder{
        val binding = createBinding(parent, viewType)
        return ProfileViewHolder(binding)
    }

    private fun createBinding(parent: ViewGroup, viewType: Int): LayoutTinderCardBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_tinder_card, parent,
            false
        )
    }

    override fun getItemCount() = profiles?.size ?: 0

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        profiles?.let {
            holder.binding.recommendedProfile = it[position]
            holder.binding.buttonClick = mListener

            holder.binding.executePendingBindings()

        }
    }

    fun setProfiles(profiles: List<ProfilesModel>) {
        val temp:Int = profiles.size
        this.profiles = profiles
        notifyItemRangeInserted(temp,profiles.size)
    }

    class ProfileViewHolder(val binding: LayoutTinderCardBinding) :
        RecyclerView.ViewHolder(binding.root)


    interface OnRecyclerButtonClickedListener
    {
        fun onLikedButtonClicked(uuid:String,profile: ProfilesModel)
        fun onDisLikedButtonClicked(uuid:String,profile: ProfilesModel)
    }



}