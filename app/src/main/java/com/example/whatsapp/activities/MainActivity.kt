package com.example.whatsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whatsapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var mAuth: FirebaseAuth? = null
    var user: FirebaseUser? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        super.onCreate(savedInstanceState)

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