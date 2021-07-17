package com.example.postcall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewComments : AppCompatActivity() {
    var postId=0
    lateinit var tvpostbody:TextView
    lateinit var tvpostTitle:TextView
    lateinit var rvComments:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_comments)
         postId=intent.getIntExtra("Post_id",0)
        castViews()
        getPost()
        getComments()
    }
    fun castViews(){
        tvpostbody=findViewById(R.id.tvPostBody)
        tvpostTitle=findViewById(R.id.tvPostTitle)
    }
    fun getPost(){

        if (postId==0){
            Toast.makeText(baseContext,"page not found",Toast.LENGTH_SHORT).show()
            finish()
        }

        val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        val request=apiClient.getPost(postId)
        request.enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    var post=response.body()
                    tvpostTitle.text=post?.title
                    tvpostbody.text=post?.body

                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_SHORT).show()
            }

        })
    }


    fun getComments() {
        rvComments=findViewById(R.id.rvComments)

        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getComments(postId)
        request.enqueue(object : Callback<List<Comments>?> {
            override fun onResponse(
                call: Call<List<Comments>?>,
                response: Response<List<Comments>?>
            ) {
                if (response.isSuccessful){
                    val comments=response.body()!!
                    val myCommentsAdapter = CommentsAdapter(comments,baseContext)
                    rvComments.adapter = myCommentsAdapter
                    rvComments.layoutManager = LinearLayoutManager(baseContext)
                    Toast.makeText(baseContext,"${comments.size} Comments",Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<List<Comments>?>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_SHORT).show()
            }
        })
        getPost()
    }
    }


