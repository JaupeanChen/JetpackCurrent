package com.example.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var text: TextView
    private lateinit var viewModel: MainViewModel

//    private val model: MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(MainViewModel::class.java)

        text = findViewById(R.id.tv)
        button = findViewById(R.id.add)
        text.text = viewModel.counter.value.toString()
        button.setOnClickListener {
            viewModel.plus()
        }
        viewModel.counter.observe(this) {
            text.text = it.toString()
        }
        val addLater = findViewById<Button>(R.id.add_later)
        addLater.setOnClickListener {
            viewModel.plusLater()
        }
    }

}