package com.example.recycleview

import com.example.recycleview.model.Data
import com.example.recycleview.model.ProductClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface ApiInterface {


    @GET(value = "home/featureproducts")
    fun getData() : Call<ProductClass>

}