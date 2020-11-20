package com.example.kibo

data class UsersMatchClass(var team: Int? = null, var user: Int? = null, var kills: Int? = null, var deaths: Int? = null, var assists: Int? = null ) {
    override fun toString(): String {
        return "Team "+ team + ", User: "+ user + " K: "+ kills + " D: "+ deaths + " A: "+ assists + ""
    }
}