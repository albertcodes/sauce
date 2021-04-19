package com.example.tmsauce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(this@SplashScreen, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 5000)

    }
}