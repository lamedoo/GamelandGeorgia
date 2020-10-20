package com.lukakordzaia.gamelandgeorgia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukakordzaia.gamelandgeorgia.R
import com.lukakordzaia.gamelandgeorgia.helpers.PostsAdapter
import com.lukakordzaia.gamelandgeorgia.models.AllPostsNew
import com.lukakordzaia.gamelandgeorgia.services.PostService
import com.lukakordzaia.gamelandgeorgia.services.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_review.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewFragment : Fragment() {

    var posts: MutableList<AllPostsNew> = ArrayList()

    var page = 1
    var isLoading = false

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {


        val destinationService  = ServiceBuilder.buildService(PostService::class.java)
        val requestCall = destinationService.getAllReviewList(page)
        requestCall.enqueue(object : Callback<MutableList<AllPostsNew>> {
            override fun onResponse(
                call: Call<MutableList<AllPostsNew>>,
                response: Response<MutableList<AllPostsNew>>
            ) {
                if (response.isSuccessful) {
                    response.body()!!.forEach {
                        posts.add(it)
                    }
                    fr_recyclerView_review.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(activity)
                        adapter = PostsAdapter(posts, context)
                        sv_fragment_review.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->

                            if (scrollY == v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                                page++
                                loadNewPosts()
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        activity,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<MutableList<AllPostsNew>>, t: Throwable) {
                Toast.makeText(activity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })

        return inflater.inflate(R.layout.fragment_review, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        srl_fragment_review.setOnRefreshListener {
            Toast.makeText(activity, "Refreshed!", Toast.LENGTH_SHORT).show()
            srl_fragment_review.isRefreshing = false
        }

    }

    private fun loadNewPosts() {
        isLoading = true

        val destinationService  = ServiceBuilder.buildService(PostService::class.java)
        val requestCall = destinationService.getAllReviewList(page)
        requestCall.enqueue(object : Callback<MutableList<AllPostsNew>> {
            override fun onResponse(
                call: Call<MutableList<AllPostsNew>>,
                response: Response<MutableList<AllPostsNew>>
            ) {
                if (response.isSuccessful) {
                    response.body()!!.forEach {
                        posts.add(it)
                    }
                    fr_recyclerView_review.apply {
                        adapter?.notifyDataSetChanged()
                    }
                    isLoading = false
                }
            }

            override fun onFailure(call: Call<MutableList<AllPostsNew>>, t: Throwable) {}
        })
    }
}