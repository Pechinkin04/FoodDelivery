package com.example.fooddelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.fooddelivery.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    //private lateinit var goLoginPage : AppCompatButton
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_start)

        goLoginPage = findViewById(R.id.go_user_login_btn)
        goLoginPage.setOnClickListener{
            val intent = Intent(this@StartActivity, LoginActivity :: class.java)
            startActivity(intent)
            finish()
        }*/

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goUserLoginBtn.setOnClickListener{
            val intent = Intent(this@StartActivity, LoginActivity :: class.java)
            startActivity(intent)
            finish()
        }
    }
}