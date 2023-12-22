package com.example.testing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter( private val listener:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
val view=LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        val ViewHolder=NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked((items[ViewHolder.adapterPosition]))
        }
        return ViewHolder
    }

    override fun getItemCount(): Int {
return items.size   }
    fun updateNews(list:ArrayList<News>){
items.clear()
        items.addAll(list)
        notifyDataSetChanged()//will re-initate all three methods onCreateViewHolder,getItemCount,onBindViewHolder
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
val currItem=items[position]
        holder.titleView.text="$position -->"+currItem.title
      Glide.with(holder.imgView.context).load(currItem.imageUrl).into(holder.imgView)
    }

}

class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val titleView: TextView=itemView.findViewById(R.id.TxtVw)
  val imgView:ImageView=itemView.findViewById(R.id.img)
}

interface NewsItemClicked{
    fun onItemClicked(item:News)
}