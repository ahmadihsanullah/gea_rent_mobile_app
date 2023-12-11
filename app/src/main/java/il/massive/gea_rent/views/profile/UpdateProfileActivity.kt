package il.massive.gea_rent.views.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import il.massive.gea_rent.R
import il.massive.gea_rent.databinding.ActivityUpdateProfileBinding

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpanProfile.setOnClickListener {
            Toast.makeText(this@UpdateProfileActivity,"Update Berhasil", Toast.LENGTH_LONG).show()
            finish()
        }
        binding.btnBackprofile.setOnClickListener {
            finish()
        }
    }
}