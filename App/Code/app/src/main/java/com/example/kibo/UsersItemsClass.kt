package com.example.kibo

import java.util.*

data class UsersItemsClass(var userId: Int? = null, var itemArray: Array<ItemClass>) {
    override fun toString(): String {
        return "Item [Username=" + userId + Arrays.toString(itemArray) + "]"
    }
}