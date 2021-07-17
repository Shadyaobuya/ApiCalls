package com.example.postcall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPosts()
    }
    fun getPosts(){
        var rvPostItems=findViewById<RecyclerView>(R.id.rvPosts)
        var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)
        var request = retrofit.getPosts()
        request.enqueue(object: Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful){
                    var posts=response.body()
                    var getPostAdapter=PostAdapter(posts!!,baseContext)
                    Toast.makeText(baseContext,"${posts.size} posts",Toast.LENGTH_SHORT).show()
                    rvPostItems.adapter=getPostAdapter
                    rvPostItems.layoutManager=LinearLayoutManager(baseContext)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_SHORT).show()            }
        })
    }
}