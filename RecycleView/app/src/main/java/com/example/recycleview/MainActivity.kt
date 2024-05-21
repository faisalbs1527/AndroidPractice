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
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<DataClass>?> {
            override fun onResponse(p0: Call<List<DataClass>?>, p1: Response<List<DataClass>?>) {
                val responseBody = p1.body()!!

                recyclerView.adapter = AdapterClass(responseBody)

            }

            override fun onFailure(p0: Call<List<DataClass>?>, p1: Throwable) {
                Log.d("MainActivity","OnFailure: "+p1.message)
            }
        })
    }
}