package il.massive.gea_rent.views.autentikasi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import il.massive.gea_rent.api.RequestState
import il.massive.gea_rent.databinding.ActivityLoginBinding
import il.massive.gea_rent.helper.Constant
import il.massive.gea_rent.helper.SharedPrefrencesHelper
import il.massive.gea_rent.viewmodels.UserViewModel
import il.massive.gea_rent.views.MainActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private  var _binding : ActivityLoginBinding? = null
    private val userViewModel: UserViewModel by viewModels()
    lateinit var sharedPreferencesHelper: SharedPrefrencesHelper
    private val binding get()= _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        sharedPreferencesHelper = SharedPrefrencesHelper(this)
        hideLoading()
        binding!!.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    fun loginUser(){
        val username = binding!!.etUsername.text.toString()
        val password = binding!!.etPassword.text.toString()
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Semua data harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        //create request to server
        val requestBody = JSONObject().apply {
            put("username", username)
            put("password", password)
        }.toString().toRequestBody("application/json".toMediaTypeOrNull())

        userViewModel.userLogin(requestBody).observe(this){
               when (it) {
                    is RequestState.success -> {
                        hideLoading()
                        // Fetch current user after successful login
                        val token = it.data.token
                        Log.i("token nya nih: ", "${token}")
                           userViewModel.getCurrentUser(token).observe(this) { userState ->
                                when (userState) {
                                    is RequestState.success -> {
                                        hideLoading()
                                        //save to sharedpreferences
                                        saveUSer(token, userState.data.data.name)
                                        // User is logged in, navigate to MainActivity
                                        moveIntent()
                                    }
                                    is RequestState.loading -> {
                                        showLoading()
                                    }
                                    is RequestState.error -> {
                                        hideLoading()
                                        Log.e("UserCurrent Error", "User tidak diketahui: ${userState.message}")
                                        Toast.makeText(this, "error: ${userState.message}", Toast.LENGTH_SHORT).show()
                                    }
                                    else ->{}
                                }
                        }
                    }

                    is RequestState.loading -> showLoading()
                    is RequestState.error -> {
                        hideLoading()
                        Log.e("Register Error", "Failed to login user. Response: ${it.message}")
                        Toast.makeText(this, "error: ${it.message}", Toast.LENGTH_SHORT).show()
                    }

                    else -> {}
                }
        }
    }
    private fun showLoading() {
        binding!!.loading.show()
    }
    private fun hideLoading() {
        binding!!.loading.hide()
    }

    fun saveUSer(token: String, name:String){
        sharedPreferencesHelper.put(Constant.PREF_IS_LOGIN, true)
        sharedPreferencesHelper.put(Constant.PREF_IS_TOKEN, token)
        sharedPreferencesHelper.put(Constant.PREF_IS_NAME, name)
    }

    override fun onStart() {
        super.onStart()
        if(sharedPreferencesHelper.getBoolean(Constant.PREF_IS_LOGIN)){
            moveIntent()
        }
    }

    fun moveIntent(){
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}