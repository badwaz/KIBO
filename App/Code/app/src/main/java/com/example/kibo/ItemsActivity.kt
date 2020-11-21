package com.example.kibo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_items.*
import java.lang.reflect.Type
import java.net.URL

class ItemsActivity : AppCompatActivity() {
    var myId : Int = 0
    var userItemsArray: ArrayList<UsersItemsClass> = ArrayList()
    var userArray: ArrayList<UsersClass> = ArrayList()
    var itemsFromUser: ArrayList<ItemClass> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        setBackgroundColor()

        val sharedPrefString : SharedPreferences = this.getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
        myId = sharedPrefString.getInt(getString(R.string.id_saved), 0)

        Thread {
            val matchesResponse = URL("https://raw.githubusercontent.com/badwaz/KiboAPPJsons/main/user_items.json").readText()
            val userListType3: Type = object : TypeToken<ArrayList<UsersItemsClass?>?>() {}.type
            userItemsArray = Gson().fromJson(matchesResponse, userListType3)

            val apiResponse = URL("https://raw.githubusercontent.com/badwaz/KiboAPPJsons/main/users.json").readText()
            val userListType: Type = object : TypeToken<ArrayList<UsersClass?>?>() {}.type
            userArray = Gson().fromJson(apiResponse, userListType)

            // Now we have to POPULATE a new Matches Class array with only the matches where user appears
            runOnUiThread {
                // Get the ITEMS from the userID (UserItemsClass) and put it in new array (ItemClass)
                for(j in 0..userItemsArray.size - 1){
                    if (userItemsArray[j].userId == myId){
                        Log.d("ID USER",userItemsArray[j].userId.toString())
                        for(i in 0..userItemsArray[j].itemArray.size - 1){
                            itemsFromUser.add(userItemsArray[j].itemArray[i])
                        }
                    }
                }
                val f: Fragment = ItemsFragment(itemsFromUser)
                supportFragmentManager.beginTransaction().add(R.id.containerItem,f).commit()
            }
        }.start()
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