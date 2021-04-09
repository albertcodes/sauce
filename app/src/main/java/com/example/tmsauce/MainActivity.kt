package com.example.tmsauce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to button
        val register_button = findViewById(R.id.register_button) as Button
        // set on-click listener
        register_button.setOnClickListener {
            // your code to perform when the user clicks on the button
            // Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RegisterActivity::class.java).apply{
            }
            startActivity(intent)
        }

    }

}

