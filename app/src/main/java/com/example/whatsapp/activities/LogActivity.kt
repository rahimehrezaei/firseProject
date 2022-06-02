package com.example.whatsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.whatsapp.R
import com.example.whatsapp.databinding.ActivityCrBinding
import com.example.whatsapp.databinding.ActivityLogBinding
import com.example.whatsapp.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class LogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogBinding
    var mAuth : FirebaseAuth? = null
    var mDAtaBase : DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {
            var email = binding.loginEmail.text.toString()
            var password = binding.loginPassword.text.toString()

            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                loginUser(email, password)
            }else{
                Toast.makeText(this , "Plz Enter Your Information!" , Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loginUser(email:String , password:String){
        mAuth!!.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                task : Task<AuthResult> ->
                if (task.isSuccessful){
                    var dashboardIntent = Intent(this,DashBoradActivity::class.java)
                    var userName = email.split("@")[0]
                    dashboardIntent.putExtra("name" , userName)
                    startActivity(dashboardIntent)
                    finish()
                }else{
                    Toast.makeText(this , "Login Failed" , Toast.LENGTH_LONG).show()
                }
            }
    }
}