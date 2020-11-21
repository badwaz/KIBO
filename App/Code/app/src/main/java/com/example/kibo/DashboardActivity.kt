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
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.reflect.Type
import java.net.URL

class DashboardActivity : AppCompatActivity() {
    var color: Int = 0
    var myNickname: String = ""
    var idUser: Int = 0
    var userKills: Int = 0
    var userDeaths: Int = 0
    var userAssists: Int = 0
    var totalPoints: Int = 0
    var userWins: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val sharedPrefString : SharedPreferences = this.getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
        color = sharedPrefString.getInt(getString(R.string.background_input), 0)

        setBackgroundColor()

        val bundle = intent.extras
        val email : String? = bundle?.getString("email")

        if(email.isNullOrBlank()){
            val dash = Intent(this,LoginActivity::class.java)
            startActivity(dash)
        } else{
            initDash(email.toString())
        }

        onClickListeners()
        logOut()
    }

    private fun initDash(email: String){
        Thread {
            val apiResponse = URL("https://raw.githubusercontent.com/badwaz/KiboAPPJsons/main/users.json").readText()
            val matchesResponse = URL("https://raw.githubusercontent.com/badwaz/KiboAPPJsons/main/matches.json").readText()
            //val users = Gson().fromJson(apiResponse, UsersClass::class.java)

            runOnUiThread {
                // From the email, get the id and nickname and fill the Dashboard
                val userListType: Type = object : TypeToken<ArrayList<UsersClass?>?>() {}.type
                val userArray: ArrayList<UsersClass> = Gson().fromJson(apiResponse, userListType)

                for (user in userArray) {
                    if(user.email == email){
                        myNickname = user.nickname.toString()
                        idUser = user.id!!

                        usernameText.text = myNickname
                    }
                }

                // Puntuación. Dependiendo de la puntuación
                val userListType3: Type = object : TypeToken<ArrayList<MatchClass?>?>() {}.type
                val matchesArray: ArrayList<MatchClass> = Gson().fromJson(matchesResponse, userListType3)

                for(j in 0..matchesArray.size - 1){
                    for(i in 0..(matchesArray[j].usersMatch.size - 1))
                    {
                        if(matchesArray[j].usersMatch[i].user == idUser){
                            userKills += matchesArray[j].usersMatch[i].kills!!
                            userDeaths += matchesArray[j].usersMatch[i].deaths!!
                            userAssists += matchesArray[j].usersMatch[i].assists!!

                            if(matchesArray[j].usersMatch[i].team == matchesArray[j].idTeamWin){
                                userWins += 1
                            }
                        }
                    }
                }

                // Put the ID on shared preferences
                val sharedPrefString : SharedPreferences = getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
                val editor = sharedPrefString.edit()
                editor.putInt(getString(R.string.id_saved),idUser)
                editor.apply()
                // End

                totalPoints = userKills * (userWins + 10)
                Log.d("TOTAL POINTS", totalPoints.toString())

                killText.text = userKills.toString()
                assisText.text = userAssists.toString()
                deathText.text = userDeaths.toString()

                if(totalPoints < 100){
                    badgeRankedBackground.setBackgroundResource(R.drawable.bronze_badge)
                }
                if(totalPoints >= 100 && totalPoints < 750){
                    badgeRankedBackground.setBackgroundResource(R.drawable.silver_badge)
                }
                if(totalPoints >= 750 && totalPoints < 1000){
                    badgeRankedBackground.setBackgroundResource(R.drawable.gold_badge)
                }
                if(totalPoints >= 1000){
                    badgeRankedBackground.setBackgroundResource(R.drawable.master_badge)
                }


            }
        }.start()
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

        rankedButton.setOnClickListener {
            startActivity(Intent(this,RankingActivity::class.java))
        }

        matchButton.setOnClickListener {
            startActivity(Intent(this,MatchesActivity::class.java))
        }

        itemButton.setOnClickListener {
            startActivity(Intent(this,ItemsActivity::class.java))
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

    private fun logOut(){
        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val dash = Intent(this,LoginActivity::class.java)
            startActivity(dash)
        }
    }
}