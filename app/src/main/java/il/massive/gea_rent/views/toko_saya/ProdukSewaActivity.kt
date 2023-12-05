package il.massive.gea_rent.views.toko_saya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import il.massive.gea_rent.R

class ProdukSewaActivity : AppCompatActivity(), View.OnClickListener   {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produk_sewa)
        val btnkembalitoko: ImageButton = findViewById(R.id.backTokoSy)
        btnkembalitoko.setOnClickListener(this)
        val btnUpdateProduk:Button = findViewById(R.id.updateproduk)
        btnUpdateProduk.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.updateproduk-> {
                val intent = Intent(this@ProdukSewaActivity, TokoSayaActivity::class.java)
                startActivity(intent)
            }
            R.id.backTokoSy-> {
                val intent = Intent(this@ProdukSewaActivity, TokoSayaActivity::class.java)
                startActivity(intent)
            }
        }
    }
}