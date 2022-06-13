package com.project.discofferytemp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.project.discofferytemp.R
import com.project.discofferytemp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding:ActivityProfileBinding
    private lateinit var mauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gso = GoogleSignInOptions
            // mengatur jenis metode login yang akan di gunakan
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            // resource string yang ada pada google json
            .requestIdToken(getString(R.string.default_web_client_id))
            // menentukan data spesifik yang akan di panggil
            .requestEmail()
            .build()


        googleSignInClient = GoogleSignIn.getClient(this, gso)
        mauth = FirebaseAuth.getInstance()
        val user =mauth.currentUser




        binding.etUsername.setText(user?.email ?:"data")
        binding.etPhone.setText(user?.displayName ?:"data")
        Glide.with(this).load(user?.photoUrl).into(binding.userProfile)

        binding.tvgugel.setOnClickListener {
            mauth.signOut()
            googleSignInClient.signOut()
            val intent =Intent(this@ProfileActivity, LoginActivty::class.java)
            intent.flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }


}