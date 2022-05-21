package com.example.whatsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.whatsapp.R
import com.example.whatsapp.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
    }
}