package com.aandroid.myretrofit.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.aandroid.myretrofit.R
import com.aandroid.myretrofit.ui.albumscreen.AlbumActivity

class SpashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this@SpashActivity, AlbumActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP })
            finish()
        },2000)

    }

}