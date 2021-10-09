package com.jantonioc.userpostcomment.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jantonioc.userpostcomment.R
import com.jantonioc.userpostcomment.dto.User

class UsersAdapter(private val users: List<User>, private val context: Context): RecyclerView.Adapter<UsersAdapter.ViewHolder>(){

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
        val cardView = layoutInflater.inflate(R.layout.card_layout_user,parent,false)
        return ViewHolder(cardView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentUser = users[position]
        holder.bind(currentUser)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView){
        fun bind(user: User)
        {
            val ivFoto = itemView.findViewById<ImageView>(R.id.imageView)
            val tvNombre = itemView.findViewById<TextView>(R.id.tvTitulo)
            val tvCorreo = itemView.findViewById<TextView>(R.id.tvCorreo)

            tvNombre.text = user.name
            tvCorreo.text = user.email
            ivFoto.setOnClickListener {

            }

        }
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }
    }
}