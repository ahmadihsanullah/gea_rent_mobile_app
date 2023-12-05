package il.massive.gea_rent.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import il.massive.gea_rent.databinding.ActivityOnBoardingBinding
import il.massive.gea_rent.views.autentikasi.LoginActivity
import il.massive.gea_rent.views.autentikasi.RegisterActivity

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDaftarOnboarding.setOnClickListener {
            startActivity(Intent(this@OnBoardingActivity, RegisterActivity::class.java))
        }

        binding.tvMasukDisini.setOnClickListener {
            startActivity(Intent(this@OnBoardingActivity, LoginActivity::class.java))
        }

    }
}