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
                ProductScreenFragment(), ProductScreenFragment::class.java.getSimpleName()
            ).addToBackStack(null).commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}