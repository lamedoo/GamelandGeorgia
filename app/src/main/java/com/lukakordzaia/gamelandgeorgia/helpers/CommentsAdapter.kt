package com.lukakordzaia.gamelandgeorgia.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukakordzaia.gamelandgeorgia.R
import com.lukakordzaia.gamelandgeorgia.models.Comments
import kotlinx.android.synthetic.main.comment_item.view.*

class CommentsAdapter(private val commentList: MutableList<Comments>, context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = commentList[position]

        if (holder is MyViewHolder) {
            var content = model.content.rendered
                .replace("<p>", "", true)
                .replace("</p>", "", true)

            holder.itemView.tv_comment_name.text = model.authorName
            holder.itemView.tv_comment_content.text = content
        }
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

}