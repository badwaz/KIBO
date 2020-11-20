package com.example.kibo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.lang.reflect.Type
import java.net.URL

class DashboardActivity : AppCompatActivity() {
    var color: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val sharedPrefString : SharedPreferences = this.getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
        color = sharedPrefString.getInt(getString(R.string.background_input), 0)

        setBackgroundColor()

        // temporal
        badgeRankedBackground.setBackgroundResource(R.drawable.bronze_badge)

        val bundle = intent.extras
        val email:String? = bundle?.getString("email")

        if(email.isNullOrBlank()){
            val dash = Intent(this,LoginActivity::class.java)
            startActivity(dash)
        } else{
            initDash(email?: "")
        }

        onClickListeners()
        readFromThread()
        logOut()
    }

    private fun setBackgroundColor(){
        val sharedPrefString : SharedPreferences = this.getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
        val mytext: Int = sharedPrefString.getInt(getString(R.string.background_input), 0)

        when(mytext){
            0 -> dashBackground.setBackgroundResource(R.drawable.gradient_color)
            1 -> dashBackground.setBackgroundResource(R.drawable.gradient_color_2)
            2 -> dashBackground.setBackgroundResource(R.drawable.gradient_color_3)
            3 -> dashBackground.setBackgroundResource(R.drawable.gradient_color_4)
        }
    }

    private fun onClickListeners(){
        contactButton.setOnClickListener {
            startActivity(Intent(this,ContactActivity::class.java))
        }

        changeColor.setOnClickListener {

            val sharedPrefString : SharedPreferences = getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
            val editor = sharedPrefString.edit()

            editor.putInt(getString(R.string.background_input),color) // En lugar del 1, poner de donde lo cojo.
            editor.apply()

            color++
            if(color > 3){
                color = 0
            }

            setBackgroundColor()
        }
    }

    private fun readFromThread(){
        Thread {
            val apiResponse = URL("https://raw.githubusercontent.com/badwaz/KiboAPPJsons/main/users.json").readText()
            //val users = Gson().fromJson(apiResponse, UsersClass::class.java)

            runOnUiThread {

                val userListType: Type = object : TypeToken<ArrayList<UsersClass?>?>() {}.type
                val userArray: ArrayList<UsersClass> = Gson().fromJson(apiResponse, userListType)

                for (user in userArray) {
                    Log.d("LIST", user.toString())
                }

                /*val category = arrayOf(headers.Host,headers.traceparent)
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, category)
                listView.adapter = adapter;*/
            }
        }.start()
    }

    private fun initDash(email: String){
        // From the email, get the data and fill the Dashboard
    }

    private fun logOut(){
        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val dash = Intent(this,LoginActivity::class.java)
            startActivity(dash)
        }
    }
}