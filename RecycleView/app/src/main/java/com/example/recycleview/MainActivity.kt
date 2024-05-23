package com.example.recycleview

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.model.ProductClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private  lateinit var  recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById<RecyclerView>(R.id.recycle_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        getMyData()

    }
    private fun getMyData(){

        val apiService = ApiClient.retrofitBuilder.create(ApiInterface::class.java)

        val retrofitData = apiService.getData(
            contentType = "application/json",
            deviceId = "faisal's android",
            nst = "eyJhbGciOiJIUzUxMiJ9.eyJOU1RfS0VZIjoiYm05d1UzUmhkR2x2YmxSdmEyVnUifQ.adqiIzFjqZdpJw5uHOHjE5qw2UvCDH2FwMmwlYvr5ljKyPG65ZQe_4wb8NYEQFXmyZZyVu-77xd5Njn310cjMw"
        )

        retrofitData.enqueue(object : Callback<ProductClass?> {
            override fun onResponse(p0: Call<ProductClass?>, p1: Response<ProductClass?>) {
                val responseBody = p1.body()!!

                recyclerView.adapter = AdapterClass(responseBody.Data)

            }

            override fun onFailure(p0: Call<ProductClass?>, p1: Throwable) {
                Log.d("MainActivity","OnFailure: "+p1.message)
            }
        })
    }
}