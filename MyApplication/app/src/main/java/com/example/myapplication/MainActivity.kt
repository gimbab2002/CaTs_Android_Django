package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent : Intent = getIntent()
        val id: String? = intent.getStringExtra("ID")
        val pw: String? = intent.getStringExtra("PW")

        binding.idTextView.append(id)
        binding.pwTextView.append(pw)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}