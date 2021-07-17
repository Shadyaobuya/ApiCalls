package com.example.postcall

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(var listPost:List<Post>,var context: Context):RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var postItem=LayoutInflater.from(parent.context).inflate(R.layout.post_list_item,parent,false)
        return PostViewHolder(postItem)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var currentPost=listPost.get(position)
//        holder.tvuserid.text=currentPost.userId
//        holder.tvId.text=currentPost.id
        holder.tvTitle.text=currentPost.title
        holder.tvBody.text=currentPost.body
        holder.cdPosts.setOnClickListener {
            var intent=Intent(context,ViewComments::class.java)
            intent.putExtra("Post_id",currentPost.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listPost.size
    }
}

class PostViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
//    var tvuserid=itemView.findViewById<TextView>(R.id.tvuserid)
//    var tvId=itemView.findViewById<TextView>(R.id.tvid)
    var tvTitle=itemView.findViewById<TextView>(R.id.tvtitle)
    var tvBody=itemView.findViewById<TextView>(R.id.tvbody)
    var cdPosts=itemView.findViewById<CardView>(R.id.cdPosts)
}