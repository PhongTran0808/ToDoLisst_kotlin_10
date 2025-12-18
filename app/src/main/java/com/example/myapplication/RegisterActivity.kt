package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var repassword: EditText
    private lateinit var signup: Button
    private lateinit var signin: Button
    private lateinit var DB: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        repassword = findViewById(R.id.repassword)
        signup = findViewById(R.id.btnsignup)
        signin = findViewById(R.id.btnsignin)
        DB = DBHelper(this)

        signup.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()
            val repass = repassword.text.toString()

            if (user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tất cả các trường", Toast.LENGTH_SHORT).show()
            } else {
                if (pass == repass) {
                    if (!DB.checkusername(user)) {
                        if (DB.insertData(user, pass)) {
                            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Tài khoản đã tồn tại! vui lòng đăng nhập", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
                }
            }
        }

        signin.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
