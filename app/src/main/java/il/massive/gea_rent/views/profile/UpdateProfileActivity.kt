package il.massive.gea_rent.views.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import il.massive.gea_rent.databinding.ActivityUpdateProfileBinding

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateProfileBinding
    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }

    private fun updateProfile() {
        // Tambahkan logika untuk menyimpan profil, termasuk imageUri jika ada
        if (imageUri != null) {
            // Lakukan sesuatu dengan imageUri, misalnya mengunggah ke server
            Toast.makeText(this@UpdateProfileActivity, "updated", Toast.LENGTH_SHORT).show()
        }

        Toast.makeText(this@UpdateProfileActivity, "Update Berhasil", Toast.LENGTH_LONG).show()
        finish()
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
}
