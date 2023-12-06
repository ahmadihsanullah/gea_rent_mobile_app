package il.massive.gea_rent.views.autentikasi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import il.massive.gea_rent.api.RequestState
import il.massive.gea_rent.databinding.ActivityLoginBinding
import il.massive.gea_rent.viewmodels.UserViewModel
import il.massive.gea_rent.views.MainActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private  var _binding : ActivityLoginBinding? = null
    private val userViewModel: UserViewModel by viewModels()
    private val binding get()= _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

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

        val requestBody = JSONObject().apply {
            put("username", username)
            put("password", password)
        }.toString().toRequestBody("application/json".toMediaTypeOrNull())

        userViewModel.userLogin(requestBody).observe(this){state->
            when(state){
                is RequestState.success -> {
                    hideLoading()
                    // Fetch current user after successful login
                    userViewModel.getCurrentUser("${state.data.data?.token}").observe(this) { userState ->
                        when (userState) {
                            is RequestState.success -> {
                                hideLoading()
                                // User is logged in, navigate to MainActivity
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java)
                                    .putExtra("name", userState.data.data?.name))
                            }
                            is RequestState.loading -> {
                                // Handle loading if needed
                                showLoading()
                            }
                            is RequestState.error -> {
                                hideLoading()
                                // Handle error if needed
                                Log.e("UserCurrent Error", "User tidak diketahui: ${userState.message}")
                                Toast.makeText(this, "error: ${userState.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                is RequestState.loading -> showLoading()
                is RequestState.error ->{
                    hideLoading()
                    Log.e("Register Error", "Failed to login user. Response: ${state.message}")
                    Toast.makeText(this, "error: ${state.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun showLoading() {
        binding!!.loading.show()
    }
    private fun hideLoading() {
        binding!!.loading.hide()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}