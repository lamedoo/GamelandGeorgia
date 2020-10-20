package com.lukakordzaia.gamelandgeorgia.fragments

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.lukakordzaia.gamelandgeorgia.R
import com.lukakordzaia.gamelandgeorgia.activities.MainActivity
import com.lukakordzaia.gamelandgeorgia.helpers.AppPreferences
import com.lukakordzaia.gamelandgeorgia.models.UserInfo
import com.lukakordzaia.gamelandgeorgia.models.UserResponse
import com.lukakordzaia.gamelandgeorgia.models.getUser
import com.lukakordzaia.gamelandgeorgia.services.PostService
import com.lukakordzaia.gamelandgeorgia.services.ServiceBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.login_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {
    var userId = 0
    var userToken = ""
    var userNick = ""

    var username = ""
    var password = ""
    var userInfo = getUser(username, password)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpFragment()

        login_button.setOnClickListener {
            username = login_username.text.toString()
            password = login_password.text.toString()
            hideKeyboard()

            if (username.isNotBlank() && password.isNotBlank()) {

            }

            userInfo = getUser(username, password)
            userLogin(userInfo)

            setUpFragment()
        }
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setUpFragment() {
        if (AppPreferences.isLogin) {
//            userInfo = getUser(AppPreferences.username, AppPreferences.password)
//            userLogin(userInfo)

            getUserInfo(AppPreferences.userId)
        } else {
            profile_login.visibility = View.VISIBLE
        }
    }

    private fun userLogin(userInfo: getUser) {
        val destinationService  = ServiceBuilder.buildService(PostService::class.java)
        val requestCall = destinationService.getUser(userInfo)
        requestCall.enqueue(object : Callback<UserResponse> {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    val userLogin = response.body()!!
                    userId = userLogin.data.id
                    userToken = userLogin.data.token
                    userNick = userLogin.data.nicename

                    if (userLogin.statusCode == 200) {
                        AppPreferences.isLogin = true
                        AppPreferences.username = username
                        AppPreferences.userId = userId.toString()
                        AppPreferences.token = userToken

                        getUserInfo(userId.toString())

                    } else {
                        Toast.makeText(context, "არასწორი ნიკი ან პაროლი", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Log.d("loginUserProblem", "${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("loginUserProblem1", "$t")
            }
        })
    }

    private fun getUserInfo(id: String) {
        profile_login.visibility = View.GONE
        profile_container.visibility = View.VISIBLE

        val destinationService  = ServiceBuilder.buildService(PostService::class.java)
        val profileCall = destinationService.getSingleProfile(id)
        profileCall.enqueue(object : Callback<UserInfo> {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(
                call: Call<UserInfo>, response: Response<UserInfo>
            ) {
                if (response.isSuccessful) {
                    val userInfo = response.body()!!

                    Picasso.get().load(userInfo.avatarUrls.x96).into(
                        profile_picture
                    )
                    profile_username.text = userInfo.slug

                    btn_profile_logout.setOnClickListener {

                        profile_container.visibility = View.GONE
                        profile_login.visibility = View.VISIBLE

                        if (AppPreferences.isLogin) {
                            AppPreferences.isLogin = false
                            AppPreferences.userId = ""
                            AppPreferences.username = ""
                            AppPreferences.password = ""
                            AppPreferences.token = ""
                        }

                    }
                } else {
                    Log.d("userInfoProblem", "${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.d("userInfoProblem", "$t")
            }
        })
    }

}