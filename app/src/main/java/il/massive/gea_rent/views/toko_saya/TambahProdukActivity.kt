package il.massive.gea_rent.views.toko_saya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import il.massive.gea_rent.R

class TambahProdukActivity : AppCompatActivity(), View.OnClickListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_produk)
        val btnSimpanProduk: Button = findViewById(R.id.simpan_produk)
        btnSimpanProduk.setOnClickListener(this)
        val btnBackToko:ImageButton = findViewById(R.id.backTokoSaya)
        btnBackToko.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.simpan_produk-> {
                val intent = Intent(this@TambahProdukActivity, TokoSayaActivity::class.java)
                startActivity(intent)
            }
            R.id.backTokoSaya-> {
                val intent = Intent(this@TambahProdukActivity, TokoSayaActivity::class.java)
                startActivity(intent)
            }
        }
    }
}