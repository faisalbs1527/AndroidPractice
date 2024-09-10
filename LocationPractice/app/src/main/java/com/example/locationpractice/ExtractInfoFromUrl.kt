package com.example.locationpractice

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object ExtractInfoFromUrl {
    fun resolveShortenedUrl(shortUrl: String, callback: (String) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder().url(shortUrl).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    val resolvedUrl = response.request.url.toString()
                    callback(resolvedUrl)
                }
            }
        })
    }
    fun extractLatLngFromUrl(url: String): Pair<Double, Double>? {
        val regex = Regex("""@(-?\d+\.\d+),(-?\d+\.\d+)""")
        val matchResult = regex.find(url) ?: return null
        val (lat, lng) = matchResult.destructured
        return Pair(lat.toDouble(), lng.toDouble())
    }
}