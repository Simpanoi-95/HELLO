package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       tvRegister.setOnClickListener{
           val intent=Intent(baseContext,RegistrationActivity::class.java)
           startActivity(intent)

       }

        btnLOGIN.setOnClickListener{
            var Name=etName.text.toString()
            var Password=etPassword.text.toString()
            Toast.makeText(baseContext,Password,Toast.LENGTH_SHORT).show()
        }




        }
}
