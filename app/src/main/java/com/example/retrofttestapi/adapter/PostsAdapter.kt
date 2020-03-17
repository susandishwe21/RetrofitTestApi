package com.example.retrofttestapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofttestapi.R
import com.example.retrofttestapi.model.Posts
import kotlinx.android.synthetic.main.item_posts.view.*

class PostsAdapter(var postList: List<Posts>) :
    RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    var mClickListener : ClickListener ?=null

    fun setClickListener(clickListener: ClickListener){
        this.mClickListener = clickListener
    }

    inner class PostsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),View.OnClickListener {
        private lateinit var listPost:Posts

        init {
            itemView.setOnClickListener(this)
        }
        fun bind(posts: Posts) {
            this.listPost = posts
            itemView.txtTitle.text = posts.title
        }

        override fun onClick(view: View?) {
            mClickListener?.onClick(listPost)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_posts, parent, false)
        return PostsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    interface ClickListener{
        fun onClick(posts : Posts)
    }
}