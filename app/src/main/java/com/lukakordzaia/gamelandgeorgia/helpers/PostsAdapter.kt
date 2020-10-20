package com.lukakordzaia.gamelandgeorgia.helpers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.lukakordzaia.gamelandgeorgia.R
import com.lukakordzaia.gamelandgeorgia.activities.MainActivity
import com.lukakordzaia.gamelandgeorgia.fragments.SinglePostFragment
import com.lukakordzaia.gamelandgeorgia.fragments.SingleVideoFragment
import com.lukakordzaia.gamelandgeorgia.models.AllPostsNew
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_item.view.*


class PostsAdapter(private val postsList: MutableList<AllPostsNew>, context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = postsList[position]

        if (holder is MyViewHolder) {
            var title = model.title.rendered
                .replace("&#8217;", "'", true)
                .replace("&#8211;", "-", true)
                .replace("&#038;", "&", true)

            var excerpt = model.excerpt.rendered
                .replace("<p>", "", true)
                .replace("&#8230;", "", true)
                .replace("&#8211;", "", true)
                .replace("&#038;", "&", true)
                .replace("&#8230;</p>", "", true)
                .substring(0, 90)

            holder.itemView.tv_post_title.text = title
            Picasso.get().load(model.jetpackFeaturedMediaUrl).into(holder.itemView.iv_post_image)
            holder.itemView.tv_post_excerpt.text = "$excerpt..."

            holder.itemView.ll_share.setOnClickListener {
                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_SUBJECT, "Sharing Post Link")
                share.putExtra(Intent.EXTRA_TEXT, model.link)
                startActivity(it.context, Intent.createChooser(share, "Share Link"), null)
            }

            if (!model.post_ratings_average.isNullOrEmpty()) {
                holder.itemView.iv_post_image_shadow.visibility = View.VISIBLE
                holder.itemView.tv_post_review_score.visibility = View.VISIBLE
                holder.itemView.tv_post_review_score.text = model.post_ratings_average
            }
            if (!model.post_video.isNullOrEmpty()) {
                holder.itemView.iv_post_image_shadow.visibility = View.VISIBLE
                holder.itemView.iv_post_video_icon.visibility = View.VISIBLE
                holder.itemView.ll_read_more_post.visibility = View.GONE
                holder.itemView.ll_read_more_video.visibility = View.VISIBLE

                holder.itemView.ll_read_more_video.setOnClickListener {
                    val activity = it.context as AppCompatActivity

                    activity.supportFragmentManager.beginTransaction()
                        .add(
                            R.id.fragment_container,
                            SingleVideoFragment().newInstance(model.id.toString())!!
                        )
                        .addToBackStack(null)
                        .commit()
                    activity.supportFragmentManager.executePendingTransactions()
                }
            } else {
                holder.itemView.ll_read_more_post.setOnClickListener {
                    val activity = it.context as AppCompatActivity

                    activity.supportFragmentManager.beginTransaction()
                        .add(
                            R.id.fragment_container,
                            SinglePostFragment().newInstance(model.id.toString())!!
                        )
                        .addToBackStack(null)
                        .commit()
                    activity.supportFragmentManager.executePendingTransactions()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}