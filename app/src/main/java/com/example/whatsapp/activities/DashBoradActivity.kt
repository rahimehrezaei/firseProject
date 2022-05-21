package com.example.whatsapp.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.whatsapp.MainActivity
import com.example.whatsapp.R
import com.example.whatsapp.adapters.SectionPagerAdapter
import com.example.whatsapp.databinding.ActivityDashBoradBinding
import com.google.firebase.auth.FirebaseAuth

class DashBoradActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashBoradBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashBoradBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        var sectionAdapter: SectionPagerAdapter? = null

        supportActionBar!!.title = "Dashboard"

        sectionAdapter = SectionPagerAdapter(supportFragmentManager)
        binding.dashboardViewPageId.adapter = sectionAdapter
        binding.mainTabs.setupWithViewPager(binding.dashboardViewPageId)
        binding.mainTabs.setTabTextColors(Color.WHITE, Color.GREEN)


        if (intent.extras != null) {
            val userName = intent!!.extras!!.getString("name")
            Toast.makeText(this, userName, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)


        menuInflater.inflate(R.menu.main_menu , menu)


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         super.onOptionsItemSelected(item)
        if (item.itemId == R.id.logoutId){
            // log out user command
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this , SecondActivity::class.java))
            finish()
        }
        if (item.itemId == R.id.settingsId){
            startActivity(Intent(this ,  SettingsActivity::class.java))
        }

        return true
    }
}