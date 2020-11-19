package com.example.kibo

data class ItemClass(var id: Int? = null, var name: String? = null, var description: String? = null, var property: String? = null, var image: String? = null) {
    override fun toString(): String {
        return "ID "+ id + ", Name: "+ name + " Description: "+ description + " Property: "+ property + " Image link: "+ image + ""
    }
}