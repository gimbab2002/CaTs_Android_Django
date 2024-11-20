package com.example.myapplication
import android.content.Intent
import android.util.Log
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.API.ApiClient
import com.example.myapplication.API.ApiService
import com.example.myapplication.API.Gender
import com.example.myapplication.API.UserRequest
import com.example.myapplication.API.UserResponse
import com.example.myapplication.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        apiService = ApiClient.retrofit.create(ApiService::class.java)
        setContentView(binding.root)


        binding.completeBtn.setOnClickListener {
            if (binding.userPw.length() != 6) {
                Toast.makeText(this@SignUp, "비밀번호는 6글자여야 합니다.", Toast.LENGTH_SHORT).show()
            } else {
                if (binding.male.isChecked() == true) {
                    val gender  = Gender.MALE.label
                    val userRequest = UserRequest(
                        binding.userName.toString(),
                        binding.userEmail.toString(),
                        binding.userId.toString(),
                        binding.userPw.toString(),
                        gender
                    )
                    registerUser(userRequest)
                } else if (binding.female.isChecked() == true) {
                    val gender  = Gender.FEMALE.label
                    val userRequest = UserRequest(
                        binding.userName.toString(),
                        binding.userEmail.toString(),
                        binding.userId.toString(),
                        binding.userPw.toString(),
                        gender
                    )
                    registerUser(userRequest)
                }
            }
        }
    }


    private fun registerUser(userRequest: UserRequest){
        apiService.registerUser(userRequest).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    Toast.makeText(this@SignUp, "User registered successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@SignUp,"Registration failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("API Error", t.message.toString())
            }
        })
    }
}