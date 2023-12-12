package com.example.jetpack

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.permissionx.PermissionX

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
            requestPermission()
        }
        viewModel.counter.observe(this) {
            text.text = it.toString()
        }
        val addLater = findViewById<Button>(R.id.add_later)
        addLater.setOnClickListener {
            viewModel.plusLater()
        }
    }

    private fun requestPermission() {
        PermissionX.request(
            this,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) { isAllGrant, deniedList ->
            if (isAllGrant) {
                try {
                    val intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel:10086")
                    startActivity(intent)
                } catch (e: SecurityException) {
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, "您拒绝了权限: $deniedList", Toast.LENGTH_LONG).show()
            }
        }
    }

}