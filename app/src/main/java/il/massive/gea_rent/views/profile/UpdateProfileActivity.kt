package il.massive.gea_rent.views.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import il.massive.gea_rent.api.RequestState
import il.massive.gea_rent.databinding.ActivityUpdateProfileBinding
import il.massive.gea_rent.helper.Constant
import il.massive.gea_rent.helper.SharedPrefrencesHelper
import il.massive.gea_rent.viewmodels.UserViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateProfileBinding
    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null
    private val userViewModel: UserViewModel by viewModels()
    lateinit var sharedPreferencesHelper: SharedPrefrencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferencesHelper = SharedPrefrencesHelper(this)

        binding.btnSimpanProfile.setOnClickListener {
            // Tambahkan logika untuk menyimpan profil dengan data yang diperbarui, termasuk imageUri jika ada
            updateProfile()
        }

        binding.editProfile.setOnClickListener {
            // Panggil metode untuk membuka galeri saat tombol edit_profile diklik
            openGallery()
        }

        binding.btnBackprofile.setOnClickListener {
            finish()
        }
        hideLoading()
    }

    private fun updateProfile() {
        // Tambahkan logika untuk menyimpan profil, termasuk imageUri jika ada
        val name =  binding.editnamaProfile.text.toString()// Gantilah dengan nama yang sesuai
        val password =  binding.editPassword.text.toString()// Gantilah dengan pasword yang sesuai


        val profileImagePart = imageUri?.let { uri ->
            val file = File(getRealPathFromURI(uri))
            val requestFile = RequestBody.create((contentResolver.getType(uri) ?: "").toMediaTypeOrNull(), file)
            MultipartBody.Part.createFormData("profile", file.name, requestFile)
        }



        //create request to server
        val requestBody = JSONObject().apply {
            put("name", name)
            put("password", password)
        }.toString().toRequestBody("application/json".toMediaTypeOrNull())

        userViewModel.updateUser(sharedPreferencesHelper.getString(Constant.PREF_IS_TOKEN).toString(),profileImagePart, requestBody).observe(this,Observer { requestState ->
            when (requestState) {
                is RequestState.loading -> {
                    // Tampilkan indikator loading
                    showLoading()
                }
                is RequestState.success -> {
                    hideLoading()
                    Toast.makeText(this@UpdateProfileActivity, "Update Berhasil", Toast.LENGTH_LONG).show()
                    finish()
                }
                is RequestState.error -> {
                    hideLoading()
                    Toast.makeText(this@UpdateProfileActivity, "Update Gagal: ${requestState.message}", Toast.LENGTH_LONG).show()
                }

                else -> {}
            }
        })
    }

    private fun getRealPathFromURI(uri: Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        val filePath = cursor?.getString(columnIndex ?: 0)
        cursor?.close()
        return filePath ?: ""
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri = data.data

            // Set gambar profil dengan gambar yang dipilih dari galeri
            binding.profile.setImageURI(imageUri)
        }
    }

    private fun showLoading() {
        binding!!.loading.show()
    }
    private fun hideLoading() {
        binding!!.loading.hide()
    }
}
