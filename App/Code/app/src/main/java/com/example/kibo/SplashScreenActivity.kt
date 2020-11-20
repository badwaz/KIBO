package com.example.kibo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.core.os.HandlerCompat.postDelayed
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        animateLogo()
        setBackgroundColor()

       Handler().postDelayed({
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right,R.anim.slide_left_out)
            finish()
        }, 2500)
    }

    private fun setBackgroundColor(){
        val sharedPrefString : SharedPreferences = this.getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
        val mytext: Int = sharedPrefString.getInt(getString(R.string.background_input), 0)

        when(mytext){
            0 -> splashBackground.setBackgroundResource(R.drawable.gradient_color)
            1 -> splashBackground.setBackgroundResource(R.drawable.gradient_color_2)
            2 -> splashBackground.setBackgroundResource(R.drawable.gradient_color_3)
            3 -> splashBackground.setBackgroundResource(R.drawable.gradient_color_4)
        }
    }

    private fun animateLogo(){
        val rotation = AnimationUtils.loadAnimation(this,R.anim.rotation_animation)
        logoSplash.animation = rotation
    }
}