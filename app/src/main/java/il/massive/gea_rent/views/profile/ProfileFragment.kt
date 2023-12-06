package il.massive.gea_rent.views.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import il.massive.gea_rent.api.RequestState
import il.massive.gea_rent.databinding.FragmentProfileBinding
import il.massive.gea_rent.helper.Constant
import il.massive.gea_rent.helper.SharedPrefrencesHelper
import il.massive.gea_rent.viewmodels.UserViewModel
import il.massive.gea_rent.views.OnBoardingActivity
import il.massive.gea_rent.views.toko_saya.TokoSayaActivity

class ProfileFragment : Fragment() {
    private  var _binding : FragmentProfileBinding? = null
    private val binding get()= _binding

    lateinit var sharedPreferencesHelper: SharedPrefrencesHelper
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        sharedPreferencesHelper = SharedPrefrencesHelper(requireContext())
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideLoading()
        binding!!.bukatoko.setOnClickListener {
            val intent = Intent(requireContext(), DaftarTokoActivity::class.java)
            startActivity(intent)
        }
        binding!!.tokosaya.setOnClickListener {
            val intent = Intent(requireContext(), TokoSayaActivity::class.java)
            startActivity(intent)
        }
        binding!!.detailProfile.setOnClickListener {
            val intent = Intent(requireContext(), DetailProfileActivity::class.java)
            startActivity(intent)
        }
        binding!!.keluar.setOnClickListener {
            logout()
        }
     }

    fun logout(){
        sharedPreferencesHelper.let {
            userViewModel.logout(sharedPreferencesHelper.getString(Constant.PREF_IS_TOKEN).toString()).observe(this){
                when(it){
                    is RequestState.success -> {
                        hideLoading()
                        sharedPreferencesHelper.clear()
                        message("berhasil keluar")
                        moveIntent()
                    }
                    is RequestState.loading -> showLoading()
                    is RequestState.error -> {
                        hideLoading()
                        Toast.makeText(requireContext(), "error: ${it.message}", Toast.LENGTH_SHORT).show()

                    }

                }
            }
        }

    }
    fun moveIntent(){
        startActivity(Intent(requireContext(), OnBoardingActivity::class.java))
    }

    fun message(message:String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding!!.loading.show()
    }
    private fun hideLoading() {
        binding!!.loading.hide()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
