package com.jantonioc.userpostcomment.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jantonioc.userpostcomment.R
import com.jantonioc.userpostcomment.dto.Post
import com.jantonioc.userpostcomment.dto.User

class PostsAdapter(private val posts: List<Post>, private val context: Context): RecyclerView.Adapter<PostsAdapter.ViewHolder>(){

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener)
    {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val cardView = layoutInflater.inflate(R.layout.card_layout_post,parent,false)
        return ViewHolder(cardView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPost = posts[position]
        holder.bind(currentPost)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class ViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView){
        fun bind(post: Post)
        {
            val tvTitulo = itemView.findViewById<TextView>(R.id.tvTitulo)
            val tvBody = itemView.findViewById<TextView>(R.id.tvBody)

            tvTitulo.text = post.title
            tvBody.text = post.body
        }
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }
    }
}