package com.example.qr_checker

import android.content.Intent
import android.media.metrics.EditingEndedEvent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.example.qr_checker.R.layout.activity_login
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth



class LoginActivity : ComponentActivity() {

    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val registertext : TextView = findViewById(R.id.textView_register_now)

        registertext.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val loginButton: Button = findViewById(R.id.button_login)

        loginButton.setOnClickListener {
            performLogin()
        }


    }
}

private fun LoginActivity.performLogin() {
    // get input from the user
    val email: EditText = findViewById(R.id.editText_email_login)
    val password: EditText = findViewById(R.id.editText_password_login)

    //null checks on inputs

    if(email.text.isEmpty() || password.text.isEmpty()){
        Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT)
            .show()
        return
    }

    val emailInput = email.text.toString()
    val passwordInput = password.text.toString()

    auth.signInWithEmailAndPassword(emailInput, passwordInput)
        .addOnCompleteListener (this){ task->
            if(task.isSuccessful){
                //Sign in success, navigate to the Main Activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                Toast.makeText(baseContext, "Success",
                    Toast.LENGTH_SHORT).show()

            } else{

                Toast.makeText(baseContext, "Authentication Failed",
                    Toast.LENGTH_SHORT).show()
            }
        }
        .addOnFailureListener {
                Toast.makeText(baseContext, "Authentication Failed. ${it.localizedMessage}",
                Toast.LENGTH_SHORT).show()
        }

}
