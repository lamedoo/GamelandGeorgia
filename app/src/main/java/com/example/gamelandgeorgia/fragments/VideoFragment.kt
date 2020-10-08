package com.example.gamelandgeorgia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamelandgeorgia.R

class VideoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

//        doAsync {
//            val json = URL("https://gameland.ge/wp-json/wp/v2/posts?categories=79,103").readText()
//            Log.d("newPosts", "$json")
//            val posts = Gson().fromJson(json, Array<AllPosts>::class.java).toMutableList()
//
//            uiThread {
//                fr_recyclerView_video.apply {
//                    setHasFixedSize(true)
//                    layoutManager = LinearLayoutManager(getActivity())
//                    adapter = PostsAdapter(posts)
//                }
//            }
//        }

        return inflater.inflate(R.layout.fragment_video, container, false)
    }
}