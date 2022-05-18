package com.example.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whatsapp.databinding.ActivityCreatAccountBinding
import com.example.whatsapp.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var mAuth: FirebaseAuth? = null
    var mDataBase: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        binding.loginBtn.setOnClickListener{

            //login user comands

        }
    }

    private fun loginUser(email : String , password:String){
        mAuth!!.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                task: Task<AuthResult> ->
                if (task.isSuccessful){
                    var dashbordIntent = Intent(this,DashBoradActivity::class.java)
                    var userName = email.split("@")[0]
                    dashbordIntent.putExtra("name" , userName)
                    startActivity(dashbordIntent)
                    finish()
                }else{
                    Toast.makeText(this , "Login Failed" , Toast.LENGTH_LONG).show()
                }
            }
    }
}