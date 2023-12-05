package il.massive.gea_rent.views.beranda

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import il.massive.gea_rent.adapter.TokoTerdekatAdapter
import il.massive.gea_rent.data.toko.DataToko
import il.massive.gea_rent.databinding.ActivityTokoTerdekatBinding
import il.massive.gea_rent.model.TokoModel
import il.massive.gea_rent.views.toko_saya.DetailTokoActivity

class TokoTerdekatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTokoTerdekatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTokoTerdekatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.icArrowBack.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView(){
        val items = DataToko.dummyTokoData

        var tokoAdapter = TokoTerdekatAdapter(items, object: TokoTerdekatAdapter.OnAdapterListener{
            override fun onClick(result: TokoModel) {
                val intent = Intent(this@TokoTerdekatActivity, DetailTokoActivity::class.java)
                intent.putExtra("gambar_toko", result.image)
                intent.putExtra("nama_toko", result.nama)
                intent.putExtra("alamat_toko", result.alamat)
                intent.putExtra("no_telepon", result.telpon)
                startActivity(intent)

                }
        })
        binding.rvTokoTerdekatLihatSemua.apply {
            layoutManager = LinearLayoutManager(this@TokoTerdekatActivity)
            adapter = tokoAdapter
        }

    }


}