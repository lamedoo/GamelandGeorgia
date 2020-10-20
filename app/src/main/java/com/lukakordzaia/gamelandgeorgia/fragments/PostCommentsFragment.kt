package com.lukakordzaia.gamelandgeorgia.fragments

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukakordzaia.gamelandgeorgia.R
import com.lukakordzaia.gamelandgeorgia.activities.MainActivity
import com.lukakordzaia.gamelandgeorgia.helpers.AppPreferences
import com.lukakordzaia.gamelandgeorgia.helpers.CommentsAdapter
import com.lukakordzaia.gamelandgeorgia.models.AddComments
import com.lukakordzaia.gamelandgeorgia.models.AllPostsNew
import com.lukakordzaia.gamelandgeorgia.models.Comments
import com.lukakordzaia.gamelandgeorgia.models.UserResponse
import com.lukakordzaia.gamelandgeorgia.services.PostService
import com.lukakordzaia.gamelandgeorgia.services.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_post_comments.*
import kotlinx.android.synthetic.main.fragment_post_comments.ib_post_single_back
import kotlinx.android.synthetic.main.fragment_single_video.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Appendable

class PostCommentsFragment : Fragment() {
    var comments: MutableList<Comments> = ArrayList()

    val destinationService  = ServiceBuilder.buildService(PostService::class.java)
    var requestCall : Call<MutableList<Comments>>? = null

    fun newInstance(postId: String?): PostCommentsFragment? {
        val frag = PostCommentsFragment()
        val args = Bundle()
        args.putString("postId", postId)
        frag.arguments = args

        return frag
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)

        return inflater.inflate(R.layout.fragment_post_comments, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        post_comment_progressBar.visibility = View.VISIBLE

        val bundle = arguments
        var singlePostId = bundle!!.getString("postId")

        ib_post_single_back.setOnClickListener {
            activity!!.onBackPressed()
            if (requestCall != null) {
                requestCall?.cancel()
            }
        }


        if (AppPreferences.isLogin) {
            ll_add_comment.visibility = View.VISIBLE

            btn_add_comment.setOnClickListener {
                var newCommentContent = add_comment.text.toString()
                var addNewCommentBody = AddComments(AppPreferences.userId.toInt(), newCommentContent)

                addNewComment(
                    "Bearer ${AppPreferences.token}",
                    singlePostId.toString(),
                    addNewCommentBody
                )
            }
        }


        requestCall = destinationService.getPostComment(singlePostId!!)
        requestCall?.enqueue(object : Callback<MutableList<Comments>> {
            override fun onResponse(
                call: Call<MutableList<Comments>>,
                response: Response<MutableList<Comments>>
            ) {
                if (response.isSuccessful) {
                    if (response.body().isNullOrEmpty()) {
                        tv_post_comment_none.visibility = View.VISIBLE
                    } else {
                        tv_post_comment_none.visibility = View.GONE
                    }

                    post_comment_progressBar.visibility = View.GONE

                    response.body()!!.forEach {
                        comments.add(it)
                    }
                    fr_recyclerView_comment.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(activity)
                        adapter = CommentsAdapter(comments, context)
                    }
                } else {
                    Toast.makeText(
                        activity,
                        "Something went wrong1 ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<MutableList<Comments>>, t: Throwable) {
                Toast.makeText(activity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun addNewComment(token: String, postId: String, commentContent: AddComments) {
        val destinationService  = ServiceBuilder.buildService(PostService::class.java)
        val requestCall = destinationService.addComment(token, postId, commentContent)
        requestCall.enqueue(object : Callback<AddComments> {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(
                call: Call<AddComments>,
                response: Response<AddComments>
            ) {
                if (response.isSuccessful) {

                    Log.d("newComment", "New comment is successfully added")

                } else {
                    Log.d("loginUserProblem", "${response.message()}")
                }
            }

            override fun onFailure(call: Call<AddComments>, t: Throwable) {
                Log.d("loginUserProblem1", "$t")
            }
        })
    }
}