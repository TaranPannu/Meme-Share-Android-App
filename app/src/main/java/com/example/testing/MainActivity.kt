package com.example.testing

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog.TAG
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    var url1=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }

fun loadMeme(){

    val pr_bar=findViewById(R.id.pr_bar) as ProgressBar
pr_bar.visibility=View.VISIBLE
    // Instantiate the RequestQueue.
    val queue = Volley.newRequestQueue(this)
    val url = "https://meme-api.com/gimme"

// Request a string response from the provided URL.
    val JsonObjectRequest = JsonObjectRequest(
        Request.Method.GET, url,null,
        { response ->
             url1=response.getString("url")
            val img=findViewById(R.id.img) as ImageView
            Glide.with(this).load(url1).listener(object: RequestListener<Drawable>{

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    pr_bar.visibility=View.GONE
                    return false
                }
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    pr_bar.visibility=View.GONE
                    return false
                }
            }).into(img)//to handle progress bar we will add listener in Glide
        },
  {
            Toast.makeText(this,"error", Toast.LENGTH_LONG).show()

        })

// Add the request to the RequestQueue.
    queue.add(JsonObjectRequest)

}
    fun shareMeme(view: View) {
val intent =Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
    intent.putExtra(Intent.EXTRA_TEXT,"$url1")
        val chooser=Intent.createChooser(intent,"Share meme")// to select in which app u want to share it.
        startActivity(chooser)
    }
    fun nextMeme(view: View) {
        loadMeme()
    }

}