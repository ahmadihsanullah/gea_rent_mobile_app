package il.massive.gea_rent.views.beranda

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import il.massive.gea_rent.adapter.BarangAdapter
import il.massive.gea_rent.adapter.TokoAdapter
import il.massive.gea_rent.data.barang.DataBarang
import il.massive.gea_rent.data.toko.DataToko
import il.massive.gea_rent.databinding.FragmentBerandaBinding
import il.massive.gea_rent.model.BarangModel
import il.massive.gea_rent.model.TokoModel
import il.massive.gea_rent.views.toko_saya.DetailBarangActivity
import il.massive.gea_rent.views.toko_saya.DetailTokoActivity


class BerandaFragment : Fragment() {
    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBerandaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set layout manager for RecyclerView
        binding.rvTokoTerdekat.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)

        // Set adapter for RecyclerView
        binding.rvTokoTerdekat.adapter = tokoAdapter

        binding.rvBarangTerlengkap.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvBarangTerlengkap.adapter = barangAdapter

        binding.lyBerandaTerdekat.setOnClickListener {
            startActivity(Intent(requireContext(), TokoTerdekatActivity::class.java))
        }


    }
    private val tokoAdapter by lazy {
        val items = DataToko.dummyTokoData

        TokoAdapter(items, object : TokoAdapter.onAdapterListener{
            override fun onClick(result: TokoModel) {
                val intent = Intent(requireContext(), DetailTokoActivity::class.java)
                intent.putExtra("gambar_toko", result.image)
                intent.putExtra("nama_toko", result.nama)
                intent.putExtra("alamat_toko", result.alamat)
                intent.putExtra("no_telepon", result.telpon)
                startActivity(intent)
            }
        })
    }

    private val barangAdapter by lazy {
        val items = DataBarang.dummyDataBarang

        BarangAdapter(items, object: BarangAdapter.onAdapterListener{
            override fun onClick(barang: BarangModel) {
                openDetailBarang(barang)
            }

        })
    }
    private fun openDetailBarang(barang: BarangModel) {
        val intent = Intent(requireContext(), DetailBarangActivity::class.java)
        intent.putExtra("gambar_barang", barang.gambar)
        intent.putExtra("harga_barang", barang.harga)
        intent.putExtra("nama_barang", barang.nama)
        intent.putExtra("deskripsi_barang", barang.deskripsi)
        intent.putExtra("nama_toko", barang.toko.nama)
        intent.putExtra("profil_toko", barang.toko.image)
        startActivity(intent)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

