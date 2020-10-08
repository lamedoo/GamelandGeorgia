package com.example.gamelandgeorgia.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelandgeorgia.R
import com.example.gamelandgeorgia.helpers.PostsAdapter
import com.example.gamelandgeorgia.models.AllPosts
import com.example.gamelandgeorgia.models.AllPostsNew
import com.example.gamelandgeorgia.services.PostService
import com.example.gamelandgeorgia.services.ServiceBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import java.lang.reflect.Type
import java.net.URL

class HomeFragment : Fragment() {

    var posts: MutableList<AllPostsNew> = ArrayList()

    var page = 1
    var isLoading = false

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val destinationService  = ServiceBuilder.buildService(PostService::class.java)
        val requestCall = destinationService.getAllPostList(page)
        //make network call asynchronously
        requestCall.enqueue(object : Callback<MutableList<AllPostsNew>> {
            override fun onResponse(call: Call<MutableList<AllPostsNew>>, response: Response<MutableList<AllPostsNew>>) {
                if (response.isSuccessful){
                    response.body()!!.forEach {
                        posts.add(it)
                    }
                    fr_recyclerView_home.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(activity)
                        adapter = PostsAdapter(posts)
                        addOnScrollListener(object : RecyclerView.OnScrollListener() {
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                                if (dy > 0) {
                                    val visibleItemCount = (layoutManager as LinearLayoutManager).childCount
                                    val pastVisibleItem = (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                                    val total = (adapter as PostsAdapter).itemCount

                                    if (!isLoading) {
                                        if ((visibleItemCount + pastVisibleItem) >= total) {
                                            page++
                                            loadNewPosts()
                                            Log.d("pageCount", "$page")
                                        }
                                    }
                                }
                                super.onScrolled(recyclerView, dx, dy)
                            }
                        })

                    }
                }else{
                    Toast.makeText(activity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<MutableList<AllPostsNew>>, t: Throwable) {
                Toast.makeText(activity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })

        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    private fun loadNewPosts() {
        isLoading = true

        val destinationService  = ServiceBuilder.buildService(PostService::class.java)
        val requestCall = destinationService.getAllPostList(page)
        //make network call asynchronously
        requestCall.enqueue(object : Callback<MutableList<AllPostsNew>> {
            override fun onResponse(call: Call<MutableList<AllPostsNew>>, response: Response<MutableList<AllPostsNew>>) {
                if (response.isSuccessful){
                    response.body()!!.forEach {
                        posts.add(it)
                    }
                    fr_recyclerView_home.apply {
                        adapter?.notifyDataSetChanged()
                    }
                isLoading = false
                }
            }

            override fun onFailure( call: Call<MutableList<AllPostsNew>>, t: Throwable ) {

            }
        })
    }

}