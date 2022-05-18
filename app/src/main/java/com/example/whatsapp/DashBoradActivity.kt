package com.example.whatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whatsapp.databinding.ActivityDashBoradBinding

class DashBoradActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashBoradBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashBoradBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        if (intent.extras != null){
            var userName = intent!!.extras!!.getString("name")
            Toast.makeText(this,userName,Toast.LENGTH_LONG).show()
        }
    }
}