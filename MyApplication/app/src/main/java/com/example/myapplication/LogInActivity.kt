package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.API.ApiClient
import com.example.myapplication.API.ApiService
import com.example.myapplication.API.LoginRequest
import com.example.myapplication.API.LoginResponse
import com.example.myapplication.databinding.ActivityLogInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLogInBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiService = ApiClient.retrofit.create(ApiService::class.java)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myId  = "kimbab"
        val myPw  = "4775"
        val loginRequest = LoginRequest("johndoe", "password123")
//        loginUser(loginRequest)
        binding.logInButton.setOnClickListener(){
            val Id = binding.editTextId.text.toString()
            val Pw = binding.editTextNumberPassword.text.toString()
            if((Id == "") && (Pw == "")){
                Toast.makeText(this@LogInActivity, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if (Id != myId || Pw != myPw){
                Toast.makeText(this@LogInActivity, "아이디나 비밀번호가 옳지 않습니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("ID", Id)
                intent.putExtra("PW", Pw)
                startActivity(intent)
            }
        }
        binding.signUpButton.setOnClickListener() {
            startActivity(Intent(this, SignUp::class.java))
        }
    }
    private fun loginUser(loginRequest: LoginRequest){
        apiService.loginUser(loginRequest).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    val intent = Intent(this@LogInActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@LogInActivity, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("API Error", t.message.toString())
            }
        })
    }
}