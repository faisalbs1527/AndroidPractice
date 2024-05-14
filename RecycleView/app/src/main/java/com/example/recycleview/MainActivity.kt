package com.example.recycleview

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private  lateinit var  recyclerView: RecyclerView
    private lateinit var data: ArrayList<DataClass>
    lateinit var imageList:Array<Int>
    lateinit var titleList: Array<String>

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

        imageList= arrayOf(
            R.drawable.person,
            R.drawable.photos,
            R.drawable.reddit,
            R.drawable.search,
            R.drawable.approve,
            R.drawable.list_view,
            R.drawable.location,
            R.drawable.setting,
            R.drawable.website,
            R.drawable.phone)
        titleList= arrayOf(
            "Person",
            "Photos",
            "Reddit",
            "Search",
            "Approve",
            "List View",
            "Location",
            "Setting",
            "Website",
            "Phone",
            )

//        data.add(DataClass(R.drawable.person,"Person"))
//        data.add(DataClass(R.drawable.photos,"Photos"))
//        data.add(DataClass(R.drawable.reddit,"Reddit"))
//        data.add(DataClass(R.drawable.search,"Search"))
//        data.add(DataClass(R.drawable.approve,"Approve"))
//        data.add(DataClass(R.drawable.list_view,"List View"))
//        data.add(DataClass(R.drawable.location,"Location"))
//        data.add(DataClass(R.drawable.setting,"Setting"))
//        data.add(DataClass(R.drawable.website,"Website"))
//        data.add(DataClass(R.drawable.phone,"Phone"))

//        val adapter = AdapterClass(data)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = adapter

        recyclerView = findViewById<RecyclerView>(R.id.recycle_view)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        data = arrayListOf<DataClass>()
        getData()
    }

    private fun getData(){
        for(i in imageList.indices){
            val dataClass = DataClass(imageList[i],titleList[i])
            data.add(dataClass)
        }
        recyclerView.adapter=AdapterClass(data)
    }
}