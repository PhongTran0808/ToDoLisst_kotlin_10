package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignup: Button
    private lateinit var DB: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        btnlogin = findViewById(R.id.btnsignin)
        btnsignup = findViewById(R.id.btnsignup)
        DB = DBHelper(this)

        btnlogin.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tất cả các trường", Toast.LENGTH_SHORT).show()
            } else {
                if (DB.checkusernamepassword(user, pass)) {
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.putExtra("username", user)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Thông tin không hợp lệ", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnsignup.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
