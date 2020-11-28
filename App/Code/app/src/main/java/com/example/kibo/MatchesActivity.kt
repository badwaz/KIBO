package com.example.kibo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_matches.*
import java.lang.reflect.Type
import java.net.URL

class MatchesActivity : AppCompatActivity() {
    val matchesToFragment: ArrayList<MatchClass> = arrayListOf()
    var myId : Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)
        setBackgroundColor()

        // Get the ID of the actual user
        val sharedPrefString : SharedPreferences = this.getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
        myId = sharedPrefString.getInt(getString(R.string.id_saved), 0)

        for(j in 0..JsonAPI.matchesArrayAPI.size - 1){
            for(i in 0..(JsonAPI.matchesArrayAPI[j].usersMatch.size - 1))
            {
                if (JsonAPI.matchesArrayAPI[j].usersMatch[i].user == myId){
                    matchesToFragment.add(JsonAPI.matchesArrayAPI[j])
                }
            }
        }

        val f: Fragment = MatchesFragment(matchesToFragment, JsonAPI.userArrayAPI, myId!!)
        supportFragmentManager.beginTransaction().add(R.id.containerMatch,f).commit()
    }

    private fun setBackgroundColor(){
        val sharedPrefString : SharedPreferences = this.getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
        val mytext: Int = sharedPrefString.getInt(getString(R.string.background_input), 0)

        when(mytext){
            0 -> matchesBackground.setBackgroundResource(R.drawable.gradient_color)
            1 -> matchesBackground.setBackgroundResource(R.drawable.gradient_color_2)
            2 -> matchesBackground.setBackgroundResource(R.drawable.gradient_color_3)
            3 -> matchesBackground.setBackgroundResource(R.drawable.gradient_color_4)
        }
    }

}