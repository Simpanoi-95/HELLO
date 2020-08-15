package com.example.hello

import ApiInterface
import RegistrationResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.etPassword
import kotlinx.android.synthetic.main.activity_registration.*
import okhttp3.*

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

        var requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("first_name", firstName)
            .addFormDataPart("last_name", lastName)
            .addFormDataPart("email", email)
            .addFormDataPart("phone_number", phoneNumber)
            .addFormDataPart("password", password)
            .build()
        registerUser(requestBody)
        Toast.makeText(baseContext,password,Toast.LENGTH_LONG).show()

//        Toast.makeText(baseContext,confirm,Toast.LENGTH_LONG).show()
//        Toast.makeText(baseContext,email,Toast.LENGTH_LONG).show()
    }
}
fun registerUser(requestBody: RequestBody) {
    var apiClient = ApiClient.buildService(ApiInterface::class.java)
    var registrationCall = apiClient.registerStudent(requestBody)
    registrationCall.enqueue(object : Callback<RegistrationResponse> {
        override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
            Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
        }
        override fun onResponse(
            call: Call<RegistrationResponse>,
            response: Response<RegistrationResponse>
        ) {
            if (response.isSuccessful) {
                Toast.makeText(baseContext, response.body()?.message, Toast.LENGTH_LONG).show()
                startActivity(Intent(baseContext, MainActivity::class.java))
            } else {
                Toast.makeText(baseContext, response.errorBody().toString(), Toast.LENGTH_LONG)
                    .show()
            }
        }
    })
}
}