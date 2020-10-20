package com.lukakordzaia.gamelandgeorgia.fragments

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.lukakordzaia.gamelandgeorgia.R
import com.lukakordzaia.gamelandgeorgia.activities.MainActivity
import com.lukakordzaia.gamelandgeorgia.models.AllPostsNew
import com.lukakordzaia.gamelandgeorgia.services.PostService
import com.lukakordzaia.gamelandgeorgia.services.ServiceBuilder
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.fragment_single_post.*
import kotlinx.android.synthetic.main.fragment_single_video.*
import kotlinx.android.synthetic.main.single_post_game_info.*
import kotlinx.android.synthetic.main.single_top_bar.*
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import org.sufficientlysecure.htmltextview.OnClickATagListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SingleVideoFragment : Fragment() {
    val destinationService  = ServiceBuilder.buildService(PostService::class.java)
    var requestCall : Call<AllPostsNew>? = null

    fun newInstance(postId: String?): SingleVideoFragment? {
        val frag = SingleVideoFragment()
        val args = Bundle()
        args.putString("postId", postId)
        frag.arguments = args

        return frag
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)

        return inflater.inflate(R.layout.fragment_single_video, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bundle = arguments
        var singlePostId = bundle!!.getString("postId")

        ib_post_single_back.setOnClickListener {
            activity!!.onBackPressed()
            onDestroyView()
            if (requestCall != null) {
                requestCall?.cancel()
            }
        }

        ib_post_comment.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, PostCommentsFragment().newInstance(singlePostId)!!)
                .addToBackStack(null)
                .commit()
            activity!!.supportFragmentManager.executePendingTransactions()
        }

        requestCall = destinationService.getSinglePost(singlePostId.toString())
        requestCall?.enqueue(object : Callback<AllPostsNew> {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(
                call: Call<AllPostsNew>,
                response: Response<AllPostsNew>
            ) {
                if (response.isSuccessful) {
                    var fetchedData = response.body()!!

                    tv_video_single_title.text = fetchedData.title.rendered
                    tv_video_single_author.text = fetchedData.embedded.author[0].name

                    if (!fetchedData.post_video.isNullOrEmpty()) {
                        val youTubePlayerView: YouTubePlayerView = activity!!.findViewById(R.id.yt_video_single)
                        lifecycle.addObserver(youTubePlayerView)

                        var youtubeVideo = fetchedData.post_video
                            .replace("https://youtu.be/", "", true)
                            .replace("https://youtube.com/watch?v=", "", true)
                            .replace("https://www.youtube.com/watch?v=", "", true)

                        youTubePlayerView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {

                                var videoId = youtubeVideo
                                youTubePlayer.loadVideo(videoId, 0F)
                            }
                        })
                    }

                    tv_video_single_content.setHtml(
                        fetchedData.content.rendered,
                        HtmlHttpImageGetter(tv_post_single_content, null, true)
                    )

                    tv_video_single_content.setOnClickATagListener(object : OnClickATagListener {
                        override fun onClick(
                            widget: View?,
                            spannedText: String?,
                            href: String?
                        ): Boolean {
                            activity!!.supportFragmentManager.beginTransaction()
                                .add(R.id.fragment_container, WebViewFragment().newInstance(href)!!)
                                .addToBackStack(null)
                                .commit()
                            activity!!.supportFragmentManager.executePendingTransactions()
                            return true
                        }
                    })

                    if (fetchedData.embedded.wpTerm[3].isNullOrEmpty()) {
                        tv_post_single_game.visibility = View.GONE
                    } else {
                        video_single_game_divider.visibility = View.VISIBLE
                        tv_post_single_game.text = fetchedData.embedded.wpTerm[3][0].name
                            .replace("&#8217;", "'", true)

                        var gameLink = fetchedData.embedded.wpTerm[3][0].link
                            .replace("gameland.ge/games/", "gameland.ge/game/", true)

                        tv_post_single_game.setOnClickListener {
                            activity!!.supportFragmentManager.beginTransaction()
                                .add(R.id.fragment_container, WebViewFragment().newInstance(gameLink)!!)
                                .addToBackStack(null)
                                .commit()
                            activity!!.supportFragmentManager.executePendingTransactions()

                        }
                    }

                    var consoles: TextView = view!!.findViewById(R.id.tv_post_single_consoles)
                    if (fetchedData.embedded.wpTerm[2].isNullOrEmpty()) {
                        consoles.visibility = View.GONE
                    } else {
                        video_single_game_divider.visibility = View.VISIBLE
                        fetchedData.embedded.wpTerm[2].forEach {
                            consoles.append("${it.name} / ")
                        }
                    }

                } else {
                    Log.d("SINGLE POST PROBLEM", "${response.message()}")
                }
            }

            override fun onFailure(call: Call<AllPostsNew>, t: Throwable) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        val activity = activity as MainActivity?
        activity?.hideBottomBar(false)

    }

}