package com.example.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whatsapp.activities.CrActivity
import com.example.whatsapp.activities.DashBoradActivity
import com.example.whatsapp.activities.LogActivity
import com.example.whatsapp.databinding.ActivityMaintwoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MaintwoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMaintwoBinding
    var mAuth: FirebaseAuth? = null
    var user: FirebaseUser? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaintwoBinding.inflate(layoutInflater)
        setContentView(binding.root)



        mAuth = FirebaseAuth.getInstance()

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth: FirebaseAuth ->
            user = firebaseAuth.currentUser
            if (user != null) {
                val dashbordIntent = Intent(this, DashBoradActivity::class.java)
                val userName = user!!.email!!.split("@")[0]
                dashbordIntent.putExtra("name", userName)
                startActivity(dashbordIntent)
                finish()
            } else {
                Toast.makeText(this, "Not Logged In", Toast.LENGTH_LONG).show()
            }

        }

        binding.haveaccountBtn.setOnClickListener {
            startActivity(Intent(this, LogActivity::class.java))
        }

        binding.creataccountBtn.setOnClickListener {
            startActivity(Intent(this, CrActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth!!.removeAuthStateListener(mAuthListener!!)
        }
    }
}