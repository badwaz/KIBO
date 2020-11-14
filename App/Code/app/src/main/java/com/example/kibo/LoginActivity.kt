package com.example.kibo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initAuth()
    }

    private fun initAuth(){

        signUpButton.setOnClickListener {
            val dash = Intent(this,RegisterActivity::class.java)
            startActivity(dash)
        }

        joinButton.setOnClickListener {
            if (emailText.text.isNotEmpty() && passwordText.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailText.text.toString(),passwordText.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showDashboard(it.result?.user?.email ?: "")
                    } else{
                        showAlertJoin()
                    }
                }
            }
        }

    }

    private fun showAlertJoin(){
       var builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Email or Password are incorrect")
        builder.setPositiveButton("Accept",null)
        builder.create().show()
    }

    // Used when login has been made or when the user has successfully registered
    // TODO: Create an animation to go to the next activity
    private fun showDashboard(email: String){
        val dash = Intent(this,DashboardActivity::class.java).apply {
            putExtra("email",email)
        }
        startActivity(dash)
    }
}