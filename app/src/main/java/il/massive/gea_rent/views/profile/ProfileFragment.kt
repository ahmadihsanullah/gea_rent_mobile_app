package il.massive.gea_rent.views.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import il.massive.gea_rent.databinding.FragmentProfileBinding
import il.massive.gea_rent.views.toko_saya.TokoSayaActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bukatoko.setOnClickListener {
            val intent = Intent(requireContext(), DaftarTokoActivity::class.java)
            startActivity(intent)
        }
        binding.tokosaya.setOnClickListener {
            val intent = Intent(requireContext(), TokoSayaActivity::class.java)
            startActivity(intent)
        }
        binding.detailProfile.setOnClickListener {
            val intent = Intent(requireContext(), DetailProfileActivity::class.java)
            startActivity(intent)
        }
    }



}
