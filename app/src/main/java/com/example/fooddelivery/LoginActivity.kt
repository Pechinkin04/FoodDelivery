package com.example.fooddelivery

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fooddelivery.Models.Users
import com.example.fooddelivery.databinding.ActivityLoginBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var loginBtn: Button
    private lateinit var usernameInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loadingBar: ProgressDialog

    private val parentDbName = "Users"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginBtn = findViewById<Button>(R.id.button3)
        phoneInput = findViewById<EditText>(R.id.sign_in_email)
        passwordInput = findViewById<EditText>(R.id.sign_in_password)
        loadingBar = ProgressDialog(this)


        binding.goSignUpUser.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.button3.setOnClickListener {
//            val intent = Intent(this@LoginActivity, LocationActivity::class.java)
//            startActivity(intent)
//            finish()
            loginUser()
        }
    }

    private fun loginUser() {
        val phone = phoneInput.text.toString()
        val password = passwordInput.text.toString()

        if (phone.isEmpty()) {
            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
        } else {
            loadingBar.setTitle("Вход в приложение")
            loadingBar.setMessage("Пожалуйста, подождите...")
            loadingBar.setCanceledOnTouchOutside(false)
            loadingBar.show()

            validateUser(phone, password)
        }
    }

    private fun validateUser(phone: String, password: String) {
        val rootRef = FirebaseDatabase.getInstance().reference
        rootRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(phone).exists()) {
                    // Retrieve user data
                    val usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users::class.java)

                    // Check if user data is not null
                    if (usersData != null) {
                        // Check if the phone number matches
                        if (usersData.phone == phone) {
                            // Check if the password matches
                            if (usersData.password == password) {
                                // Dismiss loading bar
                                loadingBar.dismiss()

                                // Display success message
                                Toast.makeText(this@LoginActivity, "Успешный вход!", Toast.LENGTH_SHORT).show()

                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()

                            } else {
                                // Dismiss loading bar
                                loadingBar.dismiss()

                                // Display incorrect password message
                                Toast.makeText(this@LoginActivity, "Неверный пароль", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    loadingBar.dismiss()
                    Toast.makeText(this@LoginActivity, "Аккаунт с номером $phone не существует", Toast.LENGTH_SHORT).show()
                    val registerIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
                    startActivity(registerIntent)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle onCancelled event
            }
        })
    }


}