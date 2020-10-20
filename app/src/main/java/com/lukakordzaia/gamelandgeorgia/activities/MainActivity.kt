package com.lukakordzaia.gamelandgeorgia.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.google.android.youtube.player.YouTubeBaseActivity
import com.lukakordzaia.gamelandgeorgia.R
import com.lukakordzaia.gamelandgeorgia.fragments.*
import com.lukakordzaia.gamelandgeorgia.helpers.AppPreferences
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_review.*
import kotlinx.android.synthetic.main.fragment_store.*
import kotlinx.android.synthetic.main.fragment_video.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val reviewFragment = ReviewFragment()
    private val videoFragment = VideoFragment()
    private val storeFragment = StoreFragment()
    private val profileFragment = ProfileFragment()
    private val fragmentManager = supportFragmentManager
    var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppPreferences.init(this)


        hideBottomBar(false)

        fragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, homeFragment).show(homeFragment)
        }.commit()
        initListeners()
        bottom_nav.selectedItemId = R.id.menu_home

    }

    fun hideBottomBar(isHidden: Boolean) {
        bottom_nav.visibility = if (isHidden) View.GONE else View.VISIBLE
    }

    private fun initListeners() {
        bottom_nav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    fragmentManager.beginTransaction()
                        .hide(activeFragment)
                        .show(homeFragment)
                        .commit()
                    activeFragment = homeFragment

                    if (activeFragment.isAdded && activeFragment.isVisible && activeFragment.userVisibleHint) {
                        sv_fragment_home.smoothScrollTo(0,0)
                    }
                    true
                }

                R.id.menu_review -> {
                    if (reviewFragment.isAdded) {
                        fragmentManager.beginTransaction()
                            .hide(activeFragment)
                            .show(reviewFragment)
                            .commit()
                    } else {
                        fragmentManager.beginTransaction()
                            .hide(activeFragment)
                            .add(R.id.fragment_container, reviewFragment)
                            .show(reviewFragment)
                            .commit()
                    }
                    activeFragment = reviewFragment

                    if (activeFragment.isAdded && activeFragment.isVisible && activeFragment.userVisibleHint) {
                        fr_recyclerView_review.scrollToPosition(0)
                    }
                    true
                }

                R.id.menu_video -> {
                    if (videoFragment.isAdded) {
                        fragmentManager.beginTransaction()
                            .hide(activeFragment)
                            .show(videoFragment)
                            .commit()
                    } else {
                        fragmentManager.beginTransaction()
                            .hide(activeFragment)
                            .add(R.id.fragment_container, videoFragment)
                            .show(videoFragment)
                            .commit()
                    }
                    activeFragment = videoFragment

                    if (activeFragment.isAdded && activeFragment.isVisible && activeFragment.userVisibleHint) {
                        fr_recyclerView_video.scrollToPosition(0)
                    }
                    true
                }

                R.id.menu_store -> {
                    if (storeFragment.isAdded) {
                        fragmentManager.beginTransaction()
                            .hide(activeFragment)
                            .show(storeFragment)
                            .commit()
                    } else {
                        fragmentManager.beginTransaction()
                            .hide(activeFragment)
                            .add(R.id.fragment_container, storeFragment)
                            .show(storeFragment)
                            .commit()
                    }
                    activeFragment = storeFragment
                    true
                }

                R.id.menu_profile -> {
                    if (profileFragment.isAdded) {
                        fragmentManager.beginTransaction()
                            .hide(activeFragment)
                            .show(profileFragment)
                            .commit()
                    } else {
                        fragmentManager.beginTransaction()
                            .hide(activeFragment)
                            .add(R.id.fragment_container, profileFragment)
                            .show(profileFragment)
                            .commit()
                    }
                    activeFragment = profileFragment
                    true
                }

                else -> false
            }
        }
    }
}