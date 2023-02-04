package com.example.examenlab

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examenlab.databinding.ActivityMainBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException


class MainActivity() : AppCompatActivity() {
    private val client = OkHttpClient()
    private val url = "http://127.0.0.1:8000/usersPucem/"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonData.setOnClickListener {
          end(binding.editTxtUsername.text.toString(), binding.editTxtName.text.toString())
        }

    }

    private fun end(username: String, name: String){

        val json = """
        {
        "username": "$username",
        "name": "$name"
        }
        """.trimIndent()

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = json.toRequestBody(mediaType)


        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                    // Handle failure
            }

            override fun onResponse(call: Call, response: Response) {
                Toast.makeText(this@MainActivity, "Please enter both the values", Toast.LENGTH_SHORT).show()            }
        })
    }
}