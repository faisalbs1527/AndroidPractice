package com.example.recycleview

import android.util.Log
import com.example.recycleview.model.Data
import com.example.recycleview.model.ProductClass
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

class ProductRepo {
    val apiService = ApiClient.getRetrofit().create(ApiInterface::class.java)

    val retrofitData = apiService.getData()

    fun getProducts(callback: (ProductClass?,Throwable?) -> Unit){

        retrofitData.enqueue(object : retrofit2.Callback<ProductClass?> {
            override fun onResponse(p0: Call<ProductClass?>, p1: Response<ProductClass?>) {
                val responseBody = p1.body()!!

                callback(responseBody,null)

            }

            override fun onFailure(p0: Call<ProductClass?>, p1: Throwable) {
                callback(null,p1)
//                Log.d("MainActivity","OnFailure: "+p1.message)
            }
        })
    }
}