package il.massive.gea_rent.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import il.massive.gea_rent.databinding.ActivityOnBoardingBinding
import il.massive.gea_rent.helper.Constant
import il.massive.gea_rent.helper.SharedPrefrencesHelper
import il.massive.gea_rent.views.autentikasi.LoginActivity
import il.massive.gea_rent.views.autentikasi.RegisterActivity

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    lateinit var sharedPreferencesHelper: SharedPrefrencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferencesHelper = SharedPrefrencesHelper(this@OnBoardingActivity)

        if(sharedPreferencesHelper.getBoolean(Constant.PREF_IS_LOGIN)){
            moveIntent()
            finish()
        }else{
            binding.btnDaftarOnboarding.setOnClickListener {
                startActivity(Intent(this@OnBoardingActivity, RegisterActivity::class.java))
            }

            binding.tvMasukDisini.setOnClickListener {
                startActivity(Intent(this@OnBoardingActivity, LoginActivity::class.java))
            }
        }
    }

    fun moveIntent(){
        startActivity(Intent(this@OnBoardingActivity, MainActivity::class.java))
        finish()
    }
}