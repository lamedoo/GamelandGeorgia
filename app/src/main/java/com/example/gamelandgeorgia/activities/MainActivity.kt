package com.example.gamelandgeorgia.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.gamelandgeorgia.R
import com.example.gamelandgeorgia.fragments.GameFragment
import com.example.gamelandgeorgia.fragments.HomeFragment
import com.example.gamelandgeorgia.fragments.ReviewFragment
import com.example.gamelandgeorgia.fragments.VideoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

//    private val homeFragment = HomeFragment()
//    private val reviewFragment = ReviewFragment()
//    private val videoFragment = VideoFragment()
//    private val gameFragment = GameFragment()
//    private val fragmentManager = supportFragmentManager
//    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Getting the Navigation Controller
        navController = Navigation.findNavController(this, R.id.fragment_container)

        //Setting the navigation controller to Bottom Nav
        bottom_nav.setupWithNavController(navController)



//        fragmentManager.beginTransaction().apply {
//            add(R.id.fragment_container, gameFragment).hide(gameFragment)
//            add(R.id.fragment_container, videoFragment).hide(videoFragment)
//            add(R.id.fragment_container, reviewFragment).hide(reviewFragment)
//            add(R.id.fragment_container, homeFragment).show(homeFragment)
//        }.commit()
//        initListeners()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

//    private fun initListeners() {
//        bottom_nav.setOnNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.menu_home -> {
//                    fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
//                    activeFragment = homeFragment
//                    true
//                }
//
//                R.id.menu_review -> {
//                    fragmentManager.beginTransaction().hide(activeFragment).show(reviewFragment).commit()
//                    activeFragment = reviewFragment
//                    true
//                }
//
//                R.id.menu_video -> {
//                    fragmentManager.beginTransaction().hide(activeFragment).show(videoFragment).commit()
//                    activeFragment = videoFragment
//                    true
//                }
//
//                R.id.menu_game -> {
//                    fragmentManager.beginTransaction().hide(activeFragment).show(gameFragment).commit()
//                    activeFragment = gameFragment
//                    true
//                }
//
//                else -> false
//            }
//        }
//    }
}