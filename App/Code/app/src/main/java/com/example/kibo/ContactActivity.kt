package com.example.kibo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        setBackgroundColor()

        facebookButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com")))
        }
        twitterButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.twitter.com")))
        }
        instagramButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instagram.com")))
        }
    }

    private fun setBackgroundColor(){
        val sharedPrefString : SharedPreferences = this.getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
        val mytext: Int = sharedPrefString.getInt(getString(R.string.background_input), 0)

        when(mytext){
            0 -> contactBackground.setBackgroundResource(R.drawable.gradient_color)
            1 -> contactBackground.setBackgroundResource(R.drawable.gradient_color_2)
            2 -> contactBackground.setBackgroundResource(R.drawable.gradient_color_3)
            3 -> contactBackground.setBackgroundResource(R.drawable.gradient_color_4)
        }
    }
}