

import ke.co.hello.CoursesResponse
import ke.co.hello.LoginResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiInterface {
    @POST("register")
    fun registerStudent(@Body requestBody: RequestBody): Call<RegistrationResponse>

    @POST("login")
    fun loginStudent(@Body requestBody: RequestBody): Call<LoginResponse>

    @GET("courses")
    fun getCourses(@Header("Authorization") accessToken: String): Call<CoursesResponse>



}