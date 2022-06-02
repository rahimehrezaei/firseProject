package com.example.whatsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.whatsapp.R
import com.example.whatsapp.databinding.ActivityCrBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CrActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCrBinding
    var mAuth : FirebaseAuth? = null
    var mDAtaBase : DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        binding.crButton.setOnClickListener{
            var email = binding.accountEmailInputCr.text.toString().trim()
            var password = binding.accountPasswordInputCr.text.toString().trim()
            var displayName = binding.desplayNameCr.text.toString().trim()

            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(displayName)){
                creatAccount(email, password, displayName)
            }else{
                Toast.makeText(this, "Plz Enter Your Information", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun creatAccount(email : String , password : String , displayName : String){
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                task: Task<AuthResult> ->
                if (task.isSuccessful){
                    var currentUser = mAuth!!.currentUser
                    var userId = currentUser!!.uid

                    mDAtaBase = FirebaseDatabase.getInstance().reference
                        .child("Users").child(userId)

                    var userObject = HashMap<String , String>()
                    userObject.put("display_name" , displayName)
                    userObject.put("status" ,"I'm Programmer")
                    userObject.put("image" , "default")
                    userObject.put("thumb_image", "default")

                    mDAtaBase!!.setValue(userObject).addOnCompleteListener {
                        task: Task<Void> ->
                        if (task.isSuccessful){
                            var dashboardIntent = Intent(this , DashBoradActivity::class.java)
                            dashboardIntent.putExtra("name",displayName)
                            startActivity(dashboardIntent)
                            finish()
                        }
                        else{
                            Toast.makeText(this , "User not Created", Toast.LENGTH_LONG).show()

                        }
                    }

                }else{
                        Log.d("error" , task.exception.toString())
                }
            }
    }
}