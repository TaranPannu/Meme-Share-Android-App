package com.example.testing

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest


class MainActivity2 : AppCompatActivity(), NewsItemClicked {

lateinit var adapter:Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
      var  recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager=LinearLayoutManager(this)
        data()
        adapter= Adapter(this)
        recyclerView.adapter=adapter
    }

    fun data() {
        val url="https://gnews.io/api/v4/search?q=example&token=da9a4619c5a7ff79766bfb4ff7411499"
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url,null,Response.Listener
        {
            val newsJsonArray=it.getJSONArray("articles")
val newsArray=ArrayList<News>()
            for (i in 0 until newsJsonArray.length()){
                val newsJsonObject=newsJsonArray.getJSONObject(i)
                val news=News(newsJsonObject.getString("title"),
                    newsJsonObject.getString("publishedAt"),newsJsonObject.getString("url"),newsJsonObject.getString("image"))
                newsArray.add(news)
            }
            adapter.updateNews(newsArray)
        }, Response.ErrorListener {
            Toast.makeText(this,"Error in fetching data",Toast.LENGTH_SHORT)
        })

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
// To open browser -- just need to add     implementation "androidx.browser:browser:1.7.0" that's All
        val intent = CustomTabsIntent.Builder()
            .build()
        intent.launchUrl(this, Uri.parse(item.url))
    }
}