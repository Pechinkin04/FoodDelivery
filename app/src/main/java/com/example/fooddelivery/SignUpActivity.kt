package com.example.fooddelivery

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.databinding.ActivitySignUpBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var registerBtn: Button
    private lateinit var usernameInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loadingBar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerBtn = findViewById(R.id.button3)
        usernameInput = findViewById(R.id.sign_up_user_name)
        phoneInput = findViewById(R.id.sign_in_email)
        passwordInput = findViewById(R.id.sign_in_password)
        loadingBar = ProgressDialog(this)

        binding.goLoginUserPage.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.button3.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {
        val username = usernameInput.text.toString()
        val phone = phoneInput.text.toString()
        val password = passwordInput.text.toString()

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
        } else {
            loadingBar.setTitle("Создание аккаунта")
            loadingBar.setMessage("Пожалуйста, подождите...")
            loadingBar.setCanceledOnTouchOutside(false)
            loadingBar.show()
            validatePhone(username, phone, password)
        }
    }

    private fun validatePhone(username: String, phone: String, password: String) {
        val rootRef = FirebaseDatabase.getInstance().reference
        rootRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.child("Users").child(phone).exists()) {
                    val userDataMap = HashMap<String, Any>()
                    userDataMap["phone"] = phone
                    userDataMap["name"] = username
                    userDataMap["password"] = password

                    rootRef.child("Users").child(phone).updateChildren(userDataMap)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                loadingBar.dismiss()
                                Toast.makeText(this@SignUpActivity, "Регистрация прошла успешно.", Toast.LENGTH_SHORT).show()
                                val loginIntent = Intent(this@SignUpActivity, LoginActivity::class.java)
                                startActivity(loginIntent)
                                finish()
                            } else {
                                loadingBar.dismiss()
                                Toast.makeText(this@SignUpActivity, "Ошибка.", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    // Phone number is already registered
                    loadingBar.dismiss()
                    Toast.makeText(this@SignUpActivity, "Номер $phone уже зарегистрирован", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle onCancelled event
            }
        })
    }
}