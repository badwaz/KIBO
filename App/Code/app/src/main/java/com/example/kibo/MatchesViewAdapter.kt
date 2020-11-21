package com.example.kibo

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_matches.view.*

class MatchesViewAdapter(private val values: ArrayList<MatchClass>, private val usersValues: ArrayList<UsersClass>, private val theId : Int, private val mListener: MatchesFragment.OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MatchesViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v-> val item = v.tag as MatchClass
            mListener?.onListFragmentInteraction(item)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_matches, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = values[position]
        holder.idView.text = "KDA"

        var counter: Int = 0
        for(j in 0..item.usersMatch.size - 1){
            if(item.idTeamWin == item.usersMatch[j].team && item.usersMatch[j].user == theId)
            {
                counter++
            }
        }

        if(counter > 0){
            holder.colorView.setBackgroundColor(Color.GREEN)
        }
        else{
            holder.colorView.setBackgroundColor(Color.RED)
        }

        var killsCo : Float = 0f
        var deathsCo : Float = 0f
        var assistCo : Float = 0f
        for(j in 0..item.usersMatch.size - 1){
            if(item.usersMatch[j].user == theId)
            {
                killsCo = item.usersMatch[j].kills?.toFloat() ?: 1f
                assistCo = item.usersMatch[j].assists?.toFloat() ?: 1f
                deathsCo = item.usersMatch[j].deaths?.toFloat() ?: 1f

                if(item.usersMatch[j].deaths!! <= 0){
                    deathsCo = 1F
                }

            }
        }

        holder.contentView.text = String.format("%.2f",((killsCo + assistCo)/deathsCo)) // KDA

        var userCount : Int = 0
        for(j in 0..item.usersMatch.size - 1){
            if(item.idTeamWin != item.usersMatch[j].team)
            {
                var enemyName : String = ""
                for(i in 0..usersValues.size - 1){
                    if(item.usersMatch[j].user == usersValues[i].id)
                    {
                        enemyName = usersValues[i].nickname.toString()
                    }
                }
                if(userCount == 0){
                    holder.enemy_one.text = enemyName
                }
                if(userCount == 1){
                    holder.enemy_two.text = enemyName
                }
                else{
                    holder.enemy_three.text = enemyName
                }
            userCount++
            }
        }

        with(holder.view){
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.item_number // Just KDA string
        val contentView: TextView = view.content // KDA result
        val colorView: LinearLayout = view.colorResult // Color green if win, red if lose

        val enemy_one: TextView = view.enemy1 // Enemy 1 name
        val enemy_two: TextView = view.enemy2 // Enemy 2 name
        val enemy_three: TextView = view.enemy3 // Enemy 3 name

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}