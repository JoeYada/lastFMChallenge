package com.example.joe.lastfmchallenge.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.joe.lastfmchallenge.R
import com.example.joe.lastfmchallenge.ui.album.AlbumFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        displayFragment(AlbumFragment(), false)

    }

    fun displayFragment(fragment: Fragment, addToBackstack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_container, fragment)
        if (addToBackstack) {
            fragmentTransaction.addToBackStack("")
        }
        fragmentTransaction.commit()
    }
}


