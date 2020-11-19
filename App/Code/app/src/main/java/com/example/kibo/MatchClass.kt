package com.example.kibo

import java.util.*

data class MatchClass(var id: Int? = null, var date: String? = null, var idTeamWin: Int? = null, var usersMatch: Array<UsersMatchClass>) {
    override fun toString(): String {
        return "Department [id=" + id + ", date=" + date + ", Team win=" + idTeamWin + ", users=" + Arrays.toString(usersMatch) + "]"
    }
}