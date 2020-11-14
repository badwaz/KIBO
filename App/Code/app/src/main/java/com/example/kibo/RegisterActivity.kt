package com.example.kibo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.emailText
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initAuth()
    }

    private fun initAuth(){

        registerButton.setOnClickListener {
            var pass1 : String = passwordRegisterText.text.toString()
            var pass2 : String = passwordAgainRegisterText.text.toString()
            var result :Boolean = pass1.equals(pass2)

            if (emailRegisterText.text.isNotEmpty() && passwordAgainRegisterText.text.isNotEmpty() && passwordRegisterText.text.isNotEmpty() && result){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailRegisterText.text.toString(),passwordRegisterText.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showDashboard(it.result?.user?.email ?: "")
                    } else{
                        showAlertRegister()
                    }
                }
            }
        }

        backToLogin.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showAlertRegister(){
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error registering user, check your data")
        builder.setPositiveButton("Accept",null)
        builder.create().show()
    }

    private fun showDashboard(email: String){
        val dash = Intent(this,DashboardActivity::class.java).apply {
            putExtra("email",email)
        }
        startActivity(dash)
    }
}