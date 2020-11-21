package com.example.kibo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.activity_ranking.*
import kotlinx.android.synthetic.main.activity_ranking.rankBackground

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        setBackgroundColor()
    }



    private fun setBackgroundColor(){
        val sharedPrefString : SharedPreferences = this.getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
        val mytext: Int = sharedPrefString.getInt(getString(R.string.background_input), 0)

        when(mytext){
            0 -> itemBackground.setBackgroundResource(R.drawable.gradient_color)
            1 -> itemBackground.setBackgroundResource(R.drawable.gradient_color_2)
            2 -> itemBackground.setBackgroundResource(R.drawable.gradient_color_3)
            3 -> itemBackground.setBackgroundResource(R.drawable.gradient_color_4)
        }
    }
}