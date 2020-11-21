package com.example.kibo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_ranking.*
import java.lang.reflect.Type
import java.net.URL

class RankingActivity : AppCompatActivity(), RankingFragment.OnListFragmentInteractionListener {
    val rankingArray: ArrayList<RankingClass> = arrayListOf()
    var matchesArray: ArrayList<MatchClass> = ArrayList()
    var userArray: ArrayList<UsersClass> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        setBackgroundColor()

        Thread {
            val matchesResponse = URL("https://raw.githubusercontent.com/badwaz/KiboAPPJsons/main/matches.json").readText()
            val userListType3: Type = object : TypeToken<ArrayList<MatchClass?>?>() {}.type
            matchesArray = Gson().fromJson(matchesResponse, userListType3)

            val apiResponse = URL("https://raw.githubusercontent.com/badwaz/KiboAPPJsons/main/users.json").readText()
            val userListType: Type = object : TypeToken<ArrayList<UsersClass?>?>() {}.type
            userArray = Gson().fromJson(apiResponse, userListType)

            runOnUiThread {
                for(j in 0..matchesArray.size - 1){
                    for(i in 0..(matchesArray[j].usersMatch.size - 1))
                    {
                        // Primero busco en el RANKING CLASS si existe una ID que concuerde con el user del match actual
                        val found = rankingArray.find { it.id == matchesArray[j].usersMatch[i].user}

                        // Si no es null, a ese found que nos devuelve, le sumamos los puntos a los que ya tiene
                        if(found != null){
                            found.points = found.points?.plus(matchesArray[j].usersMatch[i].kills!!)
                        }
                        else{
                            // Si es null, significa que no está insertado en el ranking ese player, por lo que en el array de usuarios
                            // ... buscamos el nombre de ese jugador por ID
                            val f = userArray.find { it.id ==  matchesArray[j].usersMatch[i].user}

                            // Si el find existe, habra encontrado al jugador del Match con la lista de usuarios
                            if(f != null){
                                // Y añadiremos al ranking la ID, su nickname y la puntuación.
                                rankingArray.add(
                                    RankingClass(
                                        f.id,
                                        f.nickname,
                                        matchesArray[j].usersMatch[i].kills
                                    )
                                )
                            }
                        }
                    }
                }

                var f: Fragment? = null
                f = RankingFragment(rankingArray)
                rankingArray.sortByDescending { it.points }
                supportFragmentManager.beginTransaction().add(R.id.containerRank,f).commit()

            }
        }.start()

        nameButton.setOnClickListener {
            rankingArray.sortBy { it.nickname }
            var tt: Fragment? = null
            tt = RankingFragment(rankingArray)
            supportFragmentManager.beginTransaction().replace(R.id.containerRank,tt).commit()
        }

        pointsButton.setOnClickListener {
            rankingArray.sortByDescending { it.points }
            var yy: Fragment? = null
            yy = RankingFragment(rankingArray)
            supportFragmentManager.beginTransaction().replace(R.id.containerRank,yy).commit()
        }
    }

    private fun setBackgroundColor(){
        val sharedPrefString : SharedPreferences = this.getSharedPreferences(
            getString(R.string.shared_preferences),
            Context.MODE_PRIVATE
        )
        val mytext: Int = sharedPrefString.getInt(getString(R.string.background_input), 0)

        when(mytext){
            0 -> rankBackground.setBackgroundResource(R.drawable.gradient_color)
            1 -> rankBackground.setBackgroundResource(R.drawable.gradient_color_2)
            2 -> rankBackground.setBackgroundResource(R.drawable.gradient_color_3)
            3 -> rankBackground.setBackgroundResource(R.drawable.gradient_color_4)
        }
    }

    override fun onListFragmentInteraction(item: RankingClass?){
        // Lo que queremos que haga cuando hacemos clic en uno de los items.
    }
}