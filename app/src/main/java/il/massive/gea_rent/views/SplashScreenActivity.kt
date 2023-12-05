package il.massive.gea_rent.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import il.massive.gea_rent.R

class SplashScreenActivity : AppCompatActivity() {

    //timer
    private val SPLASH_TIME_OUT: Long = 3000 //delay 3 detik
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity((Intent(this@SplashScreenActivity, OnBoardingActivity::class.java)))
            finish()
        }, SPLASH_TIME_OUT)
    }
}