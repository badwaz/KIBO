package com.example.kibo

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.net.URL

object JsonAPI {
    var userArrayAPI: ArrayList<UsersClass> = ArrayList()
    var matchesArrayAPI: ArrayList<MatchClass> = ArrayList()
    var userItemsArrayAPI: ArrayList<UsersItemsClass> = ArrayList()

    fun getUserData(){
        val apiResponse = URL("https://raw.githubusercontent.com/badwaz/KiboAPPJsons/main/users.json").readText()
        val userListType: Type = object : TypeToken<ArrayList<UsersClass?>?>() {}.type
        userArrayAPI = Gson().fromJson(apiResponse, userListType)
    }

    fun getMatchData(){
        val matchesResponse = URL("https://raw.githubusercontent.com/badwaz/KiboAPPJsons/main/matches.json").readText()
        val userListType3: Type = object : TypeToken<ArrayList<MatchClass?>?>() {}.type
        matchesArrayAPI= Gson().fromJson(matchesResponse, userListType3)
    }

    fun getUserItemData(){
        val matchesResponse = URL("https://raw.githubusercontent.com/badwaz/KiboAPPJsons/main/user_items.json").readText()
        val userListType3: Type = object : TypeToken<ArrayList<UsersItemsClass?>?>() {}.type
        userItemsArrayAPI = Gson().fromJson(matchesResponse, userListType3)
    }
}