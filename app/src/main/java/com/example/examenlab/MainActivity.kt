package com.example.examenlab

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import com.example.examenlab.databinding.ActivityMainBinding



class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonData.setOnClickListener {
          end(binding.editTxtUsername.text, binding.editTxtName.text)
        }

    }

    private fun end(username: Editable, name: Editable){
        val json = """
            {
                "username" : "$username",
                "name" : $name"
            }
            """

        val requestBody = json.toRequestBody("application/json; charset=utf-8".toMediaType())

        val request = okhttp3.Request.Builder()
            .url("https://www.example.com/api/login")
            .post(requestBody)
            .build()

        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()
    }
}