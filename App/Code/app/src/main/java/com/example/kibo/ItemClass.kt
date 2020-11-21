package com.example.kibo

data class ItemClass(var id: Int = 0, var name: String = "", var description: String = "", var property: String = "", var image: String = "") {
    override fun toString(): String {
        return "ID "+ id + ", Name: "+ name + " Description: "+ description + " Property: "+ property + " Image link: "+ image + ""
    }
}