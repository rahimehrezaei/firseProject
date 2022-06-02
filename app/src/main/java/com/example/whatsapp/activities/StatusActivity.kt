package com.example.whatsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whatsapp.R
import com.example.whatsapp.databinding.ActivityStatusBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference

class StatusActivity : AppCompatActivity() {
    private lateinit var binding : ActivityStatusBinding
    var mDatabase: DatabaseReference? = null
    var currentUser: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Update Status"
        if (intent.extras != null){
            var oldStatus = intent.extras!!.get("status")
            binding.statusUpdateInputId.setText(oldStatus.toString())
        }
        if (intent.extras!! == null){
            binding.statusUpdateInputId.setText("Enter Your New Status")
        }

        binding.statusUpdateBtn.setOnClickListener{
            currentUser = FirebaseAuth.getInstance().currentUser
            val userId = currentUser!!.uid
            mDatabase = FirebaseDatabase.getInstance().reference
                .child("Users")
                .child(userId)

            var status = binding.statusUpdateInputId.text.toString().trim()

            mDatabase!!.child("status")
                .setValue(status)
                .addOnCompleteListener {
                    task : Task<Void> ->
                    if (task.isSuccessful){
                        Toast.makeText(this , "Status changed successfully" , Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,SettingActivity::class.java))
                    }else{
                        Toast.makeText(this , "Status is Not Updated successfully" , Toast.LENGTH_LONG).show()
                    }
                }


        }
    }
}