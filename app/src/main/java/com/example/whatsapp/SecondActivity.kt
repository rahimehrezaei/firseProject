package com.example.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.whatsapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var bindig : ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        bindig = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(bindig.root)
        super.onCreate(savedInstanceState)

        bindig.creataccountBtn.setOnClickListener{
            startActivity(Intent(this,CreatAccountActivity::class.java))
        }
    }
}