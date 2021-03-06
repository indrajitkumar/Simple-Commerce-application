package com.elililly.codingchallage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elililly.codingchallage.screens.productScreen.ProductScreenFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fl_mainFragmentContainer,
                ProductScreenFragment(), ProductScreenFragment::class.java.simpleName
            ).addToBackStack(null).commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragmentManager = supportFragmentManager
        val currentFrag = fragmentManager.findFragmentById(R.id.fl_mainFragmentContainer)
        if (fragmentManager.backStackEntryCount == 0){
            finish()
        }
    }
}