package com.example.kibo

import java.util.*

class UsersClass(var id: Int? = null, var nickname: String? = null, var email: String? = null) {
    override fun toString(): String {
        return "ID: " + id + " Nickname: " + nickname +  "email: " + email + ""
    }
}