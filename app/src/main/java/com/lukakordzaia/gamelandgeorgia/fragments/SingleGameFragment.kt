package com.lukakordzaia.gamelandgeorgia.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.lukakordzaia.gamelandgeorgia.R
import com.lukakordzaia.gamelandgeorgia.models.AllGamesNew
import com.lukakordzaia.gamelandgeorgia.models.AllPostsNew
import com.lukakordzaia.gamelandgeorgia.services.PostService
import com.lukakordzaia.gamelandgeorgia.services.ServiceBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_single_game.*
import kotlinx.android.synthetic.main.fragment_single_post.*
import kotlinx.android.synthetic.main.single_post_game_info.*
import kotlinx.android.synthetic.main.single_top_bar.*
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import org.sufficientlysecure.htmltextview.OnClickATagListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingleGameFragment : Fragment() {
    fun newInstance(gameSlug: String?): SingleGameFragment? {
        val frag = SingleGameFragment()
        val args = Bundle()
        args.putString("gameSlug", gameSlug)
        frag.arguments = args

        return frag
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ib_post_single_back.setOnClickListener {
            activity!!.onBackPressed()
        }

        ib_post_comment.visibility = View.GONE

        val bundle = arguments
        var singleGameSlug = bundle!!.getString("gameSlug")
        Log.d("singleGame", "$singleGameSlug")

        val destinationService  = ServiceBuilder.buildService(PostService::class.java)
        val requestCall = destinationService.getSingleGame(singleGameSlug.toString())
        requestCall.enqueue(object : Callback<List<AllGamesNew>> {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(
                call: Call<List<AllGamesNew>>,
                response: Response<List<AllGamesNew>>
            ) {
                if (response.isSuccessful) {
                    var fetchedData = response.body()!![0]
                    Log.d("consoles", "${fetchedData.embedded.wpTerm[0]}")

                    Picasso.get()
                        .load(fetchedData.embedded.wpFeaturedmedia[0].sourceUrl)
                        .into(iv_single_game_image)

                    tv_single_game_name.text = fetchedData.title.rendered
                        .replace("&#8217;", "'", true)

                    if (!fetchedData.game_date.isNullOrEmpty()) {
                        tv_single_game_date.text = "გამოსვლის თარიღი: ${fetchedData.game_date}"
                    }

                    fetchedData.embedded.wpTerm[0].forEach {
                        tv_single_game_consoles.append("/ ${it.name}")
                    }

                    fetchedData.embedded.wpTerm[1].forEach {
                        tv_single_game_developer.append("/ ${it.name}")
                    }

                    fetchedData.embedded.wpTerm[2].forEach {
                        tv_single_game_publisher.append("/ ${it.name}")
                    }

                    fetchedData.embedded.wpTerm[3].forEach {
                        tv_single_game_genre.append("/ ${it.name}")
                    }


                } else {
                    Log.d("singleGamePROBLEM", "${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<AllGamesNew>>, t: Throwable) {
                Log.d("singleGamePROBLEM1", "$t")
            }
        })

    }
}