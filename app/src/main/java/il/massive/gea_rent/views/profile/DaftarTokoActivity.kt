package il.massive.gea_rent.views.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import il.massive.gea_rent.R
import il.massive.gea_rent.views.MainActivity

class DaftarTokoActivity : AppCompatActivity(), View.OnClickListener   {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_toko)
        val btnHalamanProfil: ImageButton = findViewById(R.id.backhalaman)
        btnHalamanProfil.setOnClickListener(this)
        val btnDaftarToko: Button = findViewById(R.id.btnDaftarToko)
        btnDaftarToko.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnDaftarToko-> {
                val intent = Intent(this@DaftarTokoActivity, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.backhalaman-> {
                val intent = Intent(this@DaftarTokoActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

}