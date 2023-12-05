package il.massive.gea_rent.views.autentikasi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import il.massive.gea_rent.api.RequestState
import il.massive.gea_rent.databinding.ActivityRegisterBinding
import il.massive.gea_rent.viewmodels.UserViewModel

class RegisterActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private var binding : ActivityRegisterBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
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
        val konfirmasiPassword = binding!!.etPassword.text.toString()
        if(username == "" || name == "" || password == ""){
            Toast.makeText(this, "data harus diisi terelbih dahulu", Toast.LENGTH_SHORT).show()
        }
        if(!password.equals(konfirmasiPassword)){
            Toast.makeText(this, "konfirmasi password harus sesuai dengan password", Toast.LENGTH_SHORT).show()
        }
            // Password dan konfirmasi password sesuai, lanjutkan dengan pendaftaran
            userViewModel.userRegister(username, name, password).observe(this, { state ->
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
                        Toast.makeText(this, "error: ${state.message}", Toast.LENGTH_SHORT).show()
                        // Show an error message to the user
                    }
                    is RequestState.loading -> {
                        showLoading()
                        // Handle loading state
                        // Show loading indicator to the user
                    }
                }
            })
    }

    private fun showLoading() {
        binding!!.loading.show()
    }
    private fun hideLoading() {
        binding!!.loading.hide()
    }

}