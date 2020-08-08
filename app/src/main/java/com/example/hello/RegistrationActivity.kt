package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.etPassword
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var firstname=etFirstName.text.toString()
        var lastname=etLastName.text.toString()
        var email=etEmail.text.toString()
        var phoneNumber=etPhoneNumber.text.toString()
        var password=etPassword.text.toString()
        var confirm=etConfirmPassword.text.toString()
        Toast.makeText(baseContext,password,Toast.LENGTH_LONG).show()
        Toast.makeText(baseContext,confirm,Toast.LENGTH_LONG).show()
        Toast.makeText(baseContext,email,Toast.LENGTH_LONG).show()
    }
}