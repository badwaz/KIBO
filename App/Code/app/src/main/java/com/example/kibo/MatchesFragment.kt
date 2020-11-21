package com.example.kibo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MatchesFragment(var matchesToFragment: ArrayList<MatchClass>? = null, var userArray: ArrayList<UsersClass>, var myId: Int) : Fragment() {

    fun MatchesFragment(){}
    private var columnCount = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_matches_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = matchesToFragment?.let { MatchesViewAdapter(it, userArray, myId) }
            }
        }
        return view
    }
}