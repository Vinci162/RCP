package com.example.rcp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stopToken = 1
        val startToken = 0

        val startButton = findViewById<Button>(R.id.startbutton)
        startButton.setOnClickListener {
            makeHttpRequestStart(startToken)
        }

        val stopButton = findViewById<Button>(R.id.stopbutton)
        stopButton.setOnClickListener {
            makeHttpRequestStop(stopToken)
        }
    }

    private fun makeHttpRequestStart(startToken: Int) {
        val client = OkHttpClient()

        val url = "http://10.0.2.2:8080/"
        val requestBody = FormBody.Builder()
            .add("stop_token", startToken.toString())
            .build()

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Request failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Request failed: ${response.code}", Toast.LENGTH_SHORT).show()
                    }
                }

                val responseBody = response.body?.string()
            }
        })
    }

    private fun makeHttpRequestStop(stopToken: Int) {
        val client = OkHttpClient()

        val url = "http://10.0.2.2:8080/"
        val requestBody = FormBody.Builder()
            .add("stop_token", stopToken.toString())
            .build()

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Request failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Request failed: ${response.code}", Toast.LENGTH_SHORT).show()
                    }
                }

                val responseBody = response.body?.string()
            }
        })
    }

}
