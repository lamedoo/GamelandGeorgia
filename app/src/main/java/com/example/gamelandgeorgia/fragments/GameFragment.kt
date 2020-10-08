package com.example.gamelandgeorgia.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamelandgeorgia.R
import com.example.gamelandgeorgia.helpers.PostsAdapter
import com.example.gamelandgeorgia.models.AllPosts
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_video.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

//        doAsync {
//            val json = URL("https://gameland.ge/wp-json/wp/v2/game?_fields=id,title").readText()
//            Log.d("newPosts", "$json")
//            val posts = Gson().fromJson(json, Array<AllPosts>::class.java).toMutableList()
//
//            uiThread {
//                fr_recyclerView_game.apply {
//                    setHasFixedSize(true)
//                    layoutManager = LinearLayoutManager(getActivity())
//                    adapter = PostsAdapter(posts)
//                }
//            }
//        }

        return inflater.inflate(R.layout.fragment_game, container, false)
    }
}