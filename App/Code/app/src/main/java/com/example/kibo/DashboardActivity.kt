package com.example.kibo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.lang.reflect.Type
import java.net.URL

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        badgeRankedBackground.setBackgroundResource(R.drawable.bronze_badge)

        val bundle = intent.extras
        val email:String? = bundle?.getString("email")

        if(email.isNullOrBlank()){
            val dash = Intent(this,LoginActivity::class.java)
            startActivity(dash)
        } else{
            initDash(email?: "")
        }

        readFromThread()

        logOut()
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