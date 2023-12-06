package il.massive.gea_rent.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import il.massive.gea_rent.R
import il.massive.gea_rent.databinding.ActivityMainBinding
import il.massive.gea_rent.views.beranda.BerandaFragment
import il.massive.gea_rent.views.panduan.PanduanFragment
import il.massive.gea_rent.views.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the "name" value from the intent
        val name = intent.getStringExtra("name")
        // Load the BerandaFragment and pass the "name" value
        val berandaFragment = BerandaFragment()

        loadFragment(berandaFragment).run {
            setTitles("Beranda","","")
            val bundle = Bundle().apply {
                putString("name", name)
            }
            berandaFragment.arguments = bundle
        }

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.beranda -> {
                    loadFragment(BerandaFragment())
                    setTitles("Beranda", "", "")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.panduan -> {
                    loadFragment(PanduanFragment())
                    setTitles("", "Panduan", "")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    setTitles("", "", "Profile")
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Replace with your fragment container ID
            .commit()
    }
    //set title for bottom nav
    private fun setTitles(berandaTitle: String, panduanTitle: String, profileTitle: String) {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavigationView.menu.findItem(R.id.beranda).setTitle(berandaTitle)
        bottomNavigationView.menu.findItem(R.id.panduan).setTitle(panduanTitle)
        bottomNavigationView.menu.findItem(R.id.profile).setTitle(profileTitle)
    }
}