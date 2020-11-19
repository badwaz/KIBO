package com.example.kibo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.core.os.HandlerCompat.postDelayed
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        animateLogo()

       Handler().postDelayed({
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right,R.anim.slide_left_out)
            finish()
        }, 2500)
    }

    private fun animateLogo(){
        val rotation = AnimationUtils.loadAnimation(this,R.anim.rotation_animation)
        logoSplash.animation = rotation
    }
}