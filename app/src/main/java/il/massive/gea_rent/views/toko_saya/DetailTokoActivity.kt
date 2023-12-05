package il.massive.gea_rent.views.toko_saya

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import il.massive.gea_rent.adapter.LayananAdapter
import il.massive.gea_rent.data.barang.DataBarang
import il.massive.gea_rent.databinding.ActivityDetailTokoBinding
import il.massive.gea_rent.model.BarangModel

class DetailTokoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTokoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gambar = intent.getIntExtra("gambar_toko",0)
        val nama = intent.getStringExtra("nama_toko")
        val alamat = intent.getStringExtra("alamat_toko")


        binding.ivFotoToko.setImageResource(gambar)
        binding.tvNamaToko.text = nama
        binding.tvAlamatToko.text = alamat

        binding.arrowBack.setOnClickListener {
            finish()
        }
        setUpRecyclerView()
        hubungiStore()
    }
    private fun setUpRecyclerView(){
        val items = DataBarang.dummyDataBarang

        var barangAdapter = LayananAdapter(items, object: LayananAdapter.onAdapterListener{
            override fun onClick(barang: BarangModel) {
                val intent = Intent(this@DetailTokoActivity, DetailBarangActivity::class.java)
                intent.putExtra("gambar_barang", barang.gambar)
                intent.putExtra("harga_barang", barang.harga)
                intent.putExtra("nama_barang", barang.nama)
                intent.putExtra("deskripsi_barang", barang.deskripsi)
                intent.putExtra("nama_toko", barang.toko.nama)
                intent.putExtra("profil_toko", barang.toko.image)
                startActivity(intent)
            }
        })
        binding.rvProdukToko.apply {
            layoutManager = GridLayoutManager(this@DetailTokoActivity,2)
            adapter = barangAdapter
        }

    }

    private fun hubungiStore(){
        val telpon = intent.getStringExtra("no_telepon").toString()
        val nomorWhatsApp = telpon

        binding.btnHubungiStore.setOnClickListener {
            // Ganti nomor ini dengan nomor WhatsApp toko yang sesuai

            val pesanWhatsApp = "Saya tertarik dengan produk yang berada di toko anda"
            val url = "https://api.whatsapp.com/send?phone=$nomorWhatsApp&text=${Uri.encode(pesanWhatsApp)}"

            // Buka WhatsApp dengan Intent
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}
