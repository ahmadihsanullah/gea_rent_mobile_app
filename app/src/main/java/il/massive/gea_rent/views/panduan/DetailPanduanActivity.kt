package il.massive.gea_rent.views.panduan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import il.massive.gea_rent.R
import il.massive.gea_rent.databinding.ActivityDetailPanduanBinding

class DetailPanduanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPanduanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail_panduan)
        binding = ActivityDetailPanduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gambar_panduan= intent.getIntExtra("gambar_panduan",0)
        val nama_panduan = intent.getStringExtra("nama_panduan")
        val deskripsi_panduan = intent.getStringExtra("deskripsi_panduan")

        binding.gambarPanduan.setImageResource(gambar_panduan)
        binding.namaPanduan.text = nama_panduan
        binding.deskripsiPanduan.text = deskripsi_panduan

        binding.icArrowBack.setOnClickListener {
            finish()
        }
    }
}