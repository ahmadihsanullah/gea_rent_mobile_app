package il.massive.gea_rent.views.autentikasi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import il.massive.gea_rent.api.RequestState
import il.massive.gea_rent.databinding.ActivityRegisterBinding
import il.massive.gea_rent.viewmodels.UserViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private var _binding : ActivityRegisterBinding? = null
    private val binding get()= _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // Menggunakan ID dari tombol Anda, ganti dengan yang sesuai
        val registerButton: Button = binding!!.btnDaftar
        hideLoading()
        registerButton.setOnClickListener {
            registerUser()
        }
    }

    fun registerUser(){
        val username = binding!!.etUsername.text.toString()
        val name = binding!!.etName.text.toString()
        val password = binding!!.etPassword.text.toString()
        val konfirmasiPassword = binding!!.etConfirmPassword.text.toString()
        if (username.isEmpty() || name.isEmpty() || password.isEmpty() || konfirmasiPassword.isEmpty()) {
            Toast.makeText(this, "Semua data harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        if(password.equals(konfirmasiPassword)){
            val requestBody = JSONObject().apply {
                put("username", username)
                put("password", password)
                put("name", name)
            }.toString().toRequestBody("application/json".toMediaTypeOrNull())

            userViewModel.userRegister(requestBody).observe(this) { state ->
                when (state) {
                    is RequestState.success -> {
                        hideLoading()
                        // Handle response success
                        Toast.makeText(this, "\"berhasil mendaftar\"", Toast.LENGTH_SHORT).show()
//                        val responseData = state.data
                        // Do something with the response data
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    }

                    is RequestState.error -> {
                        hideLoading()
                        // Handle error
                        Log.e("Register Error", "Failed to register user. Response: ${state.message}")
                        Toast.makeText(this, "error: ${state.message}", Toast.LENGTH_SHORT).show()
                        // Show an error message to the user
                    }

                    is RequestState.loading -> {
                        showLoading()
                        // Handle loading state
                        // Show loading indicator to the user
                    }
                }
            }
        }else{
            Toast.makeText(this, "password harus sesuai", Toast.LENGTH_SHORT).show()
            return
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