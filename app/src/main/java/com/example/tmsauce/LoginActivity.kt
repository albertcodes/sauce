package com.example.tmsauce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // get reference to button
        val createAccountButton = findViewById(R.id.create_account_button) as Button
        // set on-click listener
        createAccountButton.setOnClickListener {
            // your code to perform when the user clicks on the button
            // Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RegisterActivity::class.java).apply{
            }
            startActivity(intent)
        }


    }
}

