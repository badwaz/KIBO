package com.example.kibo

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.reflect.Type

data class ItemClass(var id: Int = 0, var name: String = "", var description: String = "", var property: String = "", var image: String = ""){}
data class UserItemsClass(var userId: Int = 0, var itemArray: Array<ItemClass>){}
data class UserClass (var id: Int = 0, var nickname: String = "", var email: String = ""){}

// This tests are for knowing how to get the items desired from JSON
class ExampleUnitTest {

    var userItemsArray: ArrayList<UsersItemsClass> = ArrayList()
    var itemsFromUser: ArrayList<ItemClass> = arrayListOf()
    @Test
    fun getId_isCorrect() {

        var userArray = ("[{'id': 0,'nickname': 'rEkeD', 'email': 'reked@esat.es'}, "
            + "{'id': 1,'nickname': 'Soriano','email': 'soriano@esat.es'}, "
            + "{'id': 2,'nickname': 'BadBoy','email': 'badboy@esat.es'}]")

        val userListType: Type = object : TypeToken<ArrayList<UserClass?>?>() {}.type
        val usy: ArrayList<UserClass> = Gson().fromJson(userArray, userListType)

        assertEquals(0, usy[0].id)
    }

    @Test
    fun getNickname_isCorrect(){
        var userArray = ("[{'id': 0,'nickname': 'rEkeD', 'email': 'reked@esat.es'}, "
                + "{'id': 1,'nickname': 'Soriano','email': 'soriano@esat.es'}, "
                + "{'id': 2,'nickname': 'BadBoy','email': 'badboy@esat.es'}]")

        val userListType: Type = object : TypeToken<ArrayList<UserClass?>?>() {}.type
        val usy: ArrayList<UserClass> = Gson().fromJson(userArray, userListType)

        assertEquals("Soriano",usy[1].nickname)
    }

    @Test
    fun getItem_isCorrect(){
        var userArray = ("[{'id': 0,'nickname': 'rEkeD', 'email': 'reked@esat.es'}, "
                + "{'id': 1,'nickname': 'Soriano','email': 'soriano@esat.es'}, "
                + "{'id': 2,'nickname': 'BadBoy','email': 'badboy@esat.es'}]")

        val userListType: Type = object : TypeToken<ArrayList<UserClass?>?>() {}.type
        val usy: ArrayList<UserClass> = Gson().fromJson(userArray, userListType)

        var userItemArray = ("[{'userId' : 0,"
            + "'itemArray' : ["
            + "{'id': 0,'name': 'Magig Hat', 'description': 'Hat of great power, received for proving your might on the battlefield', 'property': '+10 Power', 'image': 'drawable/0.png'},"
            + "{'id': 1,'name': 'Morellion', 'description': 'Wisdom of the ancient warriors who inhabited Kibulty, no one knows who wrote it.', 'property': '+8 Focus', 'image': 'drawable/0.png'},"
            + "{'id': 5,'name': 'Tangana', 'description': 'The largest and lightest sword anyone has ever built. The wielder of the sword gains great power.', 'property': '+15 Damage', 'image': 'drawable/0.png'},"
            + "{'id': 8,'name': 'Hysteria', 'description': 'This weapon collects the souls of fallen allies and increases the strength of the wielder.', 'property': '+10-30 Damage', 'image': 'drawable/0.png'}]},"
            + "{'userId' : 1,"
            + "'itemArray' : ["
            + "{'id': 4,'name': 'Abbyforth', 'description': 'A cup that you cannot drink from and which you cannot hold, but if you put it in your backpack, it cheers you up by screaming in battle.', 'property': '+15 Health', 'image': 'drawable/0.png'},"
            + "{'id': 5,'name': 'Tangana', 'description': 'The largest and lightest sword anyone has ever built. The wielder of the sword gains great power.', 'property': '+15 Damage', 'image': 'drawable/0.png'},"
            + "{'id': 6,'name': 'Double rat', 'description': 'Common poisonous daggers. Despite being very common on battlefields, their poison does not do much damage to the enemy.', 'property': '+2 Damage', 'image': 'drawable/0.png'},"
            + "{'id': 7,'name': 'Dark coat', 'description': 'It makes you go unnoticed when there are a large number of enemies.', 'property': '+8 Agility', 'image': 'drawable/0.png'},"
            + "{'id': 8,'name': 'Hysteria', 'description': 'This weapon collects the souls of fallen allies and increases the strength of the wielder.', 'property': '+10-30 Damage', 'image': 'drawable/0.png'}]},"
            + "{'userId' : 2,"
            + "'itemArray' : ["
            + "{'id': 0,'name': 'Magig Hat', 'description': 'Hat of great power, received for proving your might on the battlefield', 'property': '+10 Power', 'image': 'drawable/0.png'},"
            + "{'id': 6,'name': 'Double rat', 'description': 'Common poisonous daggers. Despite being very common on battlefields, their poison does not do much damage to the enemy.', 'property': '+2 Damage', 'image': 'drawable/0.png'},"
            + "{'id': 7,'name': 'Dark coat', 'description': 'It makes you go unnoticed when there are a large number of enemies.', 'property': '+8 Agility', 'image': 'drawable/0.png'},"
            + "{'id': 8,'name': 'Hysteria', 'description': 'This weapon collects the souls of fallen allies and increases the strength of the wielder.', 'property': '+10-30 Damage', 'image': 'drawable/0.png'}]}]")

        val userListItemType: Type = object : TypeToken<ArrayList<UsersItemsClass?>?>() {}.type
        val usrr: ArrayList<UsersItemsClass> = Gson().fromJson(userItemArray, userListItemType)

        assertEquals("Dark coat",usrr[2].itemArray[2].name)
    }


}