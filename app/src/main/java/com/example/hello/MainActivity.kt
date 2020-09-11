package ke.co.hello

import ApiInterface
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvRegister.setOnClickListener {
            val intent = Intent(baseContext, RegistrationActivity::class.java)
            startActivity(intent)
        }
            btnLOGIN.setOnClickListener {
            val email = etName.text.toString()
            val password = etPassword.text.toString()
                val error =false

                if (email.isBlank() || email.isEmpty()) {
                    error = true
                    etUserName.error = "Email is required"
                }

                if (password.isBlank() || password.isEmpty()) {
                    error = true
                    etPassword.error = "Password is required"
                }
                 progressBar.max=3000
                val currentProgress=900
                ObjectAnimator.ofInt(progressBar,propertyName:"progress",currentProgress)

                val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .build()
            
            loginUser(requestBody)
        }
    }

    fun loginUser(requestBody: RequestBody){
        val apiClient = ApiClient.buildService(ApiInterface::class.java)
        val loginCall = apiClient.loginStudent(requestBody)

        loginCall.enqueue(object :Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                progressBar.visibility= View.GONE
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    progressBar.visibility= View.GONE
                    Toast.makeText(baseContext, response.body()?.message, Toast.LENGTH_LONG).show()
                    var accessToken = response.body()?.accessToken
                    var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
                    var editor = sharedPreferences.edit()
                    editor.putString("ACCESS_TOKEN_KEY", accessToken)
                    editor.apply()
                    val intent = Intent(baseContext, CoursesActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(baseContext, response.errorBody().toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}