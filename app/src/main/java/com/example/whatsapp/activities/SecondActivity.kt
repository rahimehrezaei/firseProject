package com.example.whatsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whatsapp.databinding.ActivitySecondBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SecondActivity : AppCompatActivity() {
    var mAuth : FirebaseAuth? = null
    var user : FirebaseUser? = null
    var mAuthListener : FirebaseAuth.AuthStateListener? = null

    private lateinit var bindig : ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        bindig = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(bindig.root)
        super.onCreate(savedInstanceState)

        bindig.haveaccountBtn.setOnClickListener{
            startActivity(Intent(this , LoginActivity::class.java))
        }

        bindig.creataccountBtn.setOnClickListener{
            startActivity(Intent(this, CreatAccountActivity::class.java))
        }

        mAuth = FirebaseAuth.getInstance()

        mAuthListener = FirebaseAuth.AuthStateListener{
            firebaseAuth: FirebaseAuth ->
            user = firebaseAuth.currentUser
            if (user!= null){
                val dashbordIntent = Intent(this, DashBoradActivity::class.java)
                val userName = user!!.email!!.split("@")[0]
                dashbordIntent.putExtra("name" , userName)
                startActivity(dashbordIntent)
                finish()
            }else{
                Toast.makeText(this, "Not Logged In",Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener (mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth!!.removeAuthStateListener(mAuthListener!!)
        }
    }


}