package com.example.kibo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val bundle = intent.extras
        val email:String? = bundle?.getString("email")

        if(email.isNullOrBlank()){
            val dash = Intent(this,LoginActivity::class.java)
            startActivity(dash)
        } else{
            initDash(email?: "")
        }

        logOut()
    }

    private fun initDash(email: String){
        // From the email, get the data and fill the Dashboard
    }

    private fun logOut(){
        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val dash = Intent(this,LoginActivity::class.java)
            startActivity(dash)
        }
    }
}