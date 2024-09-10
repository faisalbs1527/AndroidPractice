package com.example.locationpractice

import android.app.Activity
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

fun getLocationDetails(activity: Activity, lat: Double, lng: Double) {
    val client = OkHttpClient()
    val url =
        "https://nominatim.openstreetmap.org/reverse?format=json&lat=$lat&lon=$lng&addressdetails=1"

    val request = Request.Builder().url(url).build()
    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
            response.use {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val jsonData = response.body?.string()
                val jsonObject = JSONObject(jsonData)
                val address = jsonObject.getJSONObject("address")
                val formattedAddress = address.optString("road", "") + ", " +
                        address.optString("suburb", "") + ", " +
                        address.optString("city", "") + ", " +
                        address.optString("state", "") + ", " +
                        address.optString("country", "") + ", " +
                        address.optString("postcode", "")
                activity.runOnUiThread {
                    // Use the address in the UI
                    println("Address: $formattedAddress")
                }
            }
        }
    })
}