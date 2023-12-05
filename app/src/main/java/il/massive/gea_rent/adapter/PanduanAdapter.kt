package il.massive.gea_rent.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import il.massive.gea_rent.R
import il.massive.gea_rent.model.BarangModel
import il.massive.gea_rent.model.PanduanModel

class PanduanAdapter(
    val panduan: List<PanduanModel>,
    val listener: PanduanAdapter.onAdapterListener
): RecyclerView.Adapter<PanduanAdapter.ViewHolder>(){
    class ViewHolder(val view: View):RecyclerView.ViewHolder(view){
        val iv_panduan = view.findViewById<ImageView>(R.id.iv_panduan)
        val judul_panduan = view.findViewById<TextView>(R.id.judul_panduan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PanduanAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.panduan_item, parent, false)
        )
    }

    override fun getItemCount() = panduan.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val panduan = panduan[position]
        holder.iv_panduan.setImageResource(panduan.gambar)
        holder.judul_panduan.text = panduan.judul
        holder.itemView.setOnClickListener{
            listener.onClick(panduan)
        }
    }
    interface onAdapterListener{
        fun onClick(result:PanduanModel)
    }
}