package com.example.myapplication111
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var dogList: MutableList<String>
    private lateinit var rvPets: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPets = findViewById<RecyclerView>(R.id.recycleView)
        dogList = mutableListOf()

        gettingDogImage()
    }



    private fun gettingDogImage(){

        val client = AsyncHttpClient()
        val url = "https://dog.ceo/api/breed/boxer/images"

        client[url, object : JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.i("dog", "error occurred")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {

                val dogImageArray = json.jsonObject.getJSONArray("message")

                for (i in 0 until dogImageArray.length()){
                    dogList.add(dogImageArray.getString(i))

                }
                Log.i("url", "$dogList")

                val adapter = dogAdapter(dogList)
                rvPets.adapter = adapter
                rvPets.layoutManager = LinearLayoutManager(this@MainActivity)
                rvPets.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))



            }


        }]




    }
}

