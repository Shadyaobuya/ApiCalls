package com.example.postcall

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentsAdapter(var commentList:List<Comments>,var context:Context):RecyclerView.Adapter<CommentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.comment_list_item,parent,false)
        return CommentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var currentcomment=commentList.get(position)
        holder.tvCommentName.text=currentcomment.name
        holder.tvCommentEmail.text=currentcomment.email
        holder.tvCommentBody.text=currentcomment.body
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

}

class CommentsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvCommentName=itemView.findViewById<TextView>(R.id.tvcommentname)
    var tvCommentEmail=itemView.findViewById<TextView>(R.id.tvcommentemail)
    var tvCommentBody=itemView.findViewById<TextView>(R.id.tvcommentbody)

}