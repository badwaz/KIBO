package com.example.kibo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_the_void.*


class TheVoidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_void)
        setBackgroundColor()
        logOut()
    }

    private fun setBackgroundColor(){
        val sharedPrefString : SharedPreferences = this.getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
        val mytext: Int = sharedPrefString.getInt(getString(R.string.background_input), 0)

        when(mytext){
            0 -> voidBackground.setBackgroundResource(R.drawable.gradient_color)
            1 -> voidBackground.setBackgroundResource(R.drawable.gradient_color_2)
            2 -> voidBackground.setBackgroundResource(R.drawable.gradient_color_3)
            3 -> voidBackground.setBackgroundResource(R.drawable.gradient_color_4)
        }
    }

    private fun logOut(){
        myExitButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val dash = Intent(this,LoginActivity::class.java)
            startActivity(dash)
        }
    }
}