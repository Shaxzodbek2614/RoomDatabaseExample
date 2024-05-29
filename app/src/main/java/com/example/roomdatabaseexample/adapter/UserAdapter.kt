package com.example.roomdatabaseexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.roomdatabaseexample.databinding.ItemRvBinding
import com.example.roomdatabaseexample.models.User

class UserAdapter(var list: ArrayList<User>, var rvAction: RvAction,):RecyclerView.Adapter<UserAdapter.Vh>() {
    inner class Vh(var itemRvBinding: ItemRvBinding):ViewHolder(itemRvBinding.root){
        fun onBind(user: User,position: Int){
            itemRvBinding.name.text =user.name
            itemRvBinding.number.text = user.nummber

            itemRvBinding.more.setOnClickListener {
                rvAction.moreClick(user, position,itemRvBinding.more)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }
    interface RvAction{
        fun moreClick(user: User,position: Int,imageView: ImageView)
    }
}