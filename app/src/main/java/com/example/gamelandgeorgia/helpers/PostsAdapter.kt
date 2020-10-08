package com.example.gamelandgeorgia.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelandgeorgia.R
import com.example.gamelandgeorgia.models.AllPosts
import com.example.gamelandgeorgia.models.AllPostsNew
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_item.view.*

class PostsAdapter(private val postsList: MutableList<AllPostsNew>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.post_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = postsList[position]

        if (holder is MyViewHolder) {
            holder.itemView.tv_post_title.text = model.title.rendered
            Picasso.get().load(model.jetpack_featured_media_url).into(holder.itemView.iv_post_image)
        }
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    private class MyViewHolder(view : View) :RecyclerView.ViewHolder(view)

}