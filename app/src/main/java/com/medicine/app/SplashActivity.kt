package com.medicine.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val  mIntent = Intent(
                this, DashboardActivity::class.java
            )
            startActivity(mIntent)
            finish()
        }, 3000)
    }
}
