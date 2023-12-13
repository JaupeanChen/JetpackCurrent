package com.example.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class JumpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jump)
        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0)
        Toast.makeText(this, "$name, $age", Toast.LENGTH_LONG).show()
    }
}