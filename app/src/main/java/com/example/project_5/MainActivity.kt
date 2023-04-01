package com.example.project_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        var imageView = findViewById<ImageView>(R.id.pokeView)
        var h = findViewById<TextView>(R.id.height)
        var w = findViewById<TextView>(R.id.weight)
        var n = findViewById<TextView>(R.id.pokeName)
        //getPokemon(h,w,n)
        getNext(button,imageView, h,w,n)
    }

    private fun getPokemon(h : TextView, w : TextView, n : TextView) {                                           //temp 54?
        val pokeApiClient = AsyncHttpClient()//https://pokeapi.co/api/v2/pokemon/{id or name}/


        pokeApiClient[url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                temp = json.jsonObject.getString("sprites")
                var t = JSONObject(temp)
                pimage = t.getString("front_default")
                var temp2 = json.jsonObject.getString("species")
                t = JSONObject(temp2)
                pokeName = t.getString("name")
                height = json.jsonObject.getString("height")
                weight = json.jsonObject.getString("weight")
                if (count<200) {
                    count++
                }else{
                    count = 1
                }

                url = "https://pokeapi.co/api/v2/pokemon/$count/"
                Log.d("Pokemon", "response successful$pimage$pokeName$height $weight")
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

    private fun getNext(button: Button, imageView: ImageView,h : TextView, w : TextView, n : TextView) {
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
    }
}