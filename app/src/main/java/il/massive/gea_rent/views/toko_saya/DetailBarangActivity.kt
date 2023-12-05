package il.massive.gea_rent.views.toko_saya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import il.massive.gea_rent.R
import il.massive.gea_rent.databinding.ActivityDetailBarangBinding

class DetailBarangActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBarangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gambar_barang = intent.getIntExtra("gambar_barang",0)
        val nama_barang = intent.getStringExtra("nama_barang")
        val deskripsi_produk = intent.getStringExtra("deskripsi_barang")
        val nama_toko = intent.getStringExtra("nama_toko")
        val profil_toko = intent.getIntExtra("profil_toko",0)

        binding.gambarProduk.setImageResource(gambar_barang)
        binding.namaProduk.text = nama_barang
        binding.deskripsiProduk.text = deskripsi_produk
        binding.namaToko.text = nama_toko
        binding.profilToko.setImageResource(profil_toko)

        binding.icArrowBack.setOnClickListener {
            finish()
        }

    }



}