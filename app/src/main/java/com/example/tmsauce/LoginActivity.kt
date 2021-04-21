package com.example.tmsauce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // get reference to button
        val createAccountButton = findViewById<Button>(R.id.create_account_button)
        val signInButton = findViewById<Button>(R.id.signin_button)
        // set on-click listener
        createAccountButton.setOnClickListener {
            // Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RegisterActivity::class.java).apply{
            }
            startActivity(intent)
        }
        // set on-click listener
        signInButton.setOnClickListener {
             Toast.makeText(this@LoginActivity, "Signing in...", Toast.LENGTH_SHORT).show()
        }

    }
}

