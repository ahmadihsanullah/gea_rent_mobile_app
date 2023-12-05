package il.massive.gea_rent.views.toko_saya

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import il.massive.gea_rent.R
import il.massive.gea_rent.databinding.ActivityTokoSayaBinding
import il.massive.gea_rent.adapter.TokoSayaAdapter
import il.massive.gea_rent.model.TokoSayaModel
import il.massive.gea_rent.views.MainActivity

class TokoSayaActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var adapter: TokoSayaAdapter
    private lateinit var binding: ActivityTokoSayaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTokoSayaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTokoSayaAdapter()
        val btnTambahProduk: Button = findViewById(R.id.produksewa_tambah)
        btnTambahProduk.setOnClickListener(this)
        val btnBackToko: ImageButton = findViewById(R.id.backToko)
        btnBackToko.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.produksewa_tambah-> {
                val intent = Intent(this@TokoSayaActivity, TambahProdukActivity::class.java)
                startActivity(intent)
            }
            R.id.backToko-> {
                val intent = Intent(this@TokoSayaActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setTokoSayaAdapter() {
        val dataList: MutableList<TokoSayaModel> = mutableListOf()

        judul().forEachIndexed { index, judul ->
            dataList.add(TokoSayaModel(image()[index], judul, tersedia()[index], harga()[index]))
        }

        // Inisialisasi adapter setelah membuat dataList
        adapter = TokoSayaAdapter(dataList, this)

        // Atur RecyclerView
        binding.recylerview.layoutManager = LinearLayoutManager(this)
        binding.recylerview.adapter = adapter
    }

    private fun judul(): Array<String> = resources.getStringArray(R.array.judul)
    private fun tersedia(): Array<String> = resources.getStringArray(R.array.tersedia)
    private fun harga(): Array<String> = resources.getStringArray(R.array.harga)
    private fun image(): List<Int> = listOf(
        R.drawable.img_item1,
        R.drawable.img_item2,
        R.drawable.img_item3,
        R.drawable.img_item4,
        R.drawable.img_item5,
        R.drawable.img_item6,
        R.drawable.img_item7
    )
}
