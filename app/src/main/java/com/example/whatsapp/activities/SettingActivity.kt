package com.example.whatsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.whatsapp.R
import com.example.whatsapp.databinding.ActivitySettingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    var mDatabase: DatabaseReference? = null
    var mStorage: StorageReference? = null
    var currentUser: FirebaseUser? = null
    var GALLERY_ID : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        supportActionBar!!.title = "Settings"
        currentUser = FirebaseAuth.getInstance().currentUser
        var userId = currentUser!!.uid
        mDatabase = FirebaseDatabase.getInstance().reference
            .child("Users")
            .child(userId)
        mDatabase!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var displayName = snapshot.child("display_name").value
                var image = snapshot.child("image").value
                var status = snapshot.child("status").value
                var thumbImage = snapshot.child("thumb_image").value

                binding.settingStatusId.text = status.toString()
                binding.settingDisplayNameId.text = displayName.toString()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        binding.settingChangeStatusId.setOnClickListener{
            var intent = Intent(this , StatusActivity::class.java)
            intent.putExtra("status" , binding.settingStatusId.text.toString().trim())
            startActivity(intent)
        }

        binding.settingChangeImageId.setOnClickListener {
            var galleryIntent = Intent()
            galleryIntent.type = "image/*"
            galleryIntent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(galleryIntent,"choose image"),GALLERY_ID)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}