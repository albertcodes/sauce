package com.example.tmsauce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // get reference to button
        val back_to_login_button = findViewById(R.id.back_to_login_button) as Button
        // set on-click listener
        back_to_login_button.setOnClickListener {
            // your code to perform when the user clicks on the button
            // Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java).apply{
            }
            startActivity(intent)
        }

    }
}