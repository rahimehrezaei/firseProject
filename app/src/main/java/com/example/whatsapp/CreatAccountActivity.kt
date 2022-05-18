package com.example.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View.inflate
import android.widget.Toast
import com.example.whatsapp.databinding.ActivityCreatAccountBinding
import com.example.whatsapp.databinding.ActivityCreatAccountBinding.inflate
import com.example.whatsapp.databinding.ActivityMainBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.log

class CreatAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatAccountBinding
    var mAuth: FirebaseAuth? = null
    var mDataBase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        binding.crButton.setOnClickListener {
            var email = binding.accountEmailInputCr.text.toString().trim()
            var password = binding.accountPasswordInputCr.text.toString().trim()
            var displayName = binding.desplayNameCr.text.toString().trim()

            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(displayName)) {
                creatAccount(email, password, displayName)

            } else {
                Toast.makeText(
                    this, "Plz Enter Your Information", Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun creatAccount(email: String, password: String, displayName: String) {
        mAuth!!.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    var currentUser = mAuth!!.currentUser
                    var userId = currentUser!!.uid

                    mDataBase = FirebaseDatabase.getInstance().reference
                        .child("Users").child(userId)

                    var userObject = HashMap<String , String>()
                    userObject.put("display_name" , displayName)
                    userObject.put("status" , "I'm Programmer...")
                    userObject.put("image","default")
                    userObject.put("thumb_image","default")

                    mDataBase!!.setValue(userObject).addOnCompleteListener {
                        task: Task<Void> ->
                        if (task.isSuccessful){
                            var dashboradIntent = Intent(this,DashBoradActivity::class.java)
                            dashboradIntent.putExtra("name",displayName)
                            startActivity(dashboradIntent)
                            finish()
                        }else{
                            Toast.makeText(this,"user isn't created",Toast.LENGTH_LONG).show()
                        }
                    }
                }
                else{

                }
            }
    }
}