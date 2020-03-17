package com.example.retrofttestapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofttestapi.adapter.PostsAdapter
import com.example.retrofttestapi.api.PostApiInterface
import com.example.retrofttestapi.model.Posts
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() ,PostsAdapter.ClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPosts()
    }
    fun loadUserList(postList: List<Posts>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            val userAdapter = PostsAdapter(postList)
            userAdapter.setClickListener(this@MainActivity)
            adapter = userAdapter

        }
    }

    fun getPosts(){

        var BASE_URL = "https://jsonplaceholder.typicode.com/"
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitService = retrofit.create(PostApiInterface::class.java)

        var apiCall = retrofitService.getPosts()
        apiCall.enqueue(object:
            Callback<List<Posts>>
        {
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                var postList = response.body()
                Log.d("Posts>>>",postList.toString())
                loadUserList(postList!!)
            }

        }
        )
    }

    override fun onClick(posts: Posts) {
        val intent = Intent(
            this@MainActivity,
            DetailPostsActivity::class.java
        )
        intent.apply {
            putExtra("TITLE", posts.title)

       }
        startActivity(intent)

    }
}
