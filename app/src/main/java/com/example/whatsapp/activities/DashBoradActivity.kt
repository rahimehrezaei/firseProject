package com.example.whatsapp.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whatsapp.adapters.SectionPagerAdapter
import com.example.whatsapp.databinding.ActivityDashBoradBinding

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
}