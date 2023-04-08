package com.example.project_5

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var count = 1
    var url = "https://pokeapi.co/api/v2/pokemon/$count/"
    var pimage = ""
    var pokeName = ""
    var temp = ""
    var height = ""
    var weight = ""

    private lateinit var pokeList: MutableList<String>
    private lateinit var heightList: MutableList<String>
    private lateinit var weightList: MutableList<String>
    private lateinit var nameList: MutableList<String>
    private lateinit var rvpoke: RecyclerView
   // private lateinit var al : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvpoke = findViewById(R.id.poke_list)
        pokeList = mutableListOf()
        heightList = mutableListOf()
        weightList = mutableListOf()
        nameList = mutableListOf()

       // val button = findViewById<Button>(R.id.button)
       // var imageView = findViewById<ImageView>(R.id.pokeView)
       // var h = findViewById<TextView>(R.id.height)
       // var w = findViewById<TextView>(R.id.weight)
        //var n = findViewById<TextView>(R.id.pokeName)
       getPokemon()
       // getNext(button,imageView, h,w,n)

    }

    @SuppressLint("SuspiciousIndentation")
    private fun getPokemon() {                                           //temp 54?
        for(i in 0 until 20) {
            val pokeApiClient = AsyncHttpClient()//https://pokeapi.co/api/v2/pokemon/{id or name}/
                pokeApiClient["https://pokeapi.co/api/v2/pokemon/$i/", object : JsonHttpResponseHandler() {
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        temp = json.jsonObject.getString("sprites")
                        var t = JSONObject(temp)
                        pimage = t.getString("front_default")

                        pokeList.add(pimage)
                        //al.add(pimage)

                        nameList.add(json.jsonObject.getString("name"))
                       /* t = JSONObject(temp2)// species: {}2keys
                        pokeName = t.getString("name")*/
                        heightList.add(json.jsonObject.getString("height"))
                        weightList.add(json.jsonObject.getString("weight"))
                        if (i==19) {
                            val adapter = pokeAdapter(pokeList,nameList,weightList,heightList)
                            rvpoke.adapter = adapter
                            rvpoke.layoutManager = LinearLayoutManager(this@MainActivity)
                        }
                        Log.d("Pokemon", "response successful$pokeList")

                    }


                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    throwable: Throwable?
                ) {
                    Log.d("Pokemon Error", errorResponse)
                }
            }]
        }


    }

   /* private fun getNext(button: Button, imageView: ImageView,h : TextView, w : TextView, n : TextView) {
        button.setOnClickListener {
            getPokemon(h,w,n)

            Glide.with(this)
                . load(pimage)
                .fitCenter()
                .into(imageView)

            h.text = "Height: $height"
            w.text = "Weight: $weight"
            n.text = pokeName
        }
    }*/
}