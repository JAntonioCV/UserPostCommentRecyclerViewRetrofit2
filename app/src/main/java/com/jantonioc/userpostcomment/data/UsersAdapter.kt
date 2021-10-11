package com.jantonioc.userpostcomment.data

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jantonioc.userpostcomment.MainActivity
import com.jantonioc.userpostcomment.R
import com.jantonioc.userpostcomment.dto.User
import java.lang.String


class UsersAdapter(private val users: ArrayList<User>, private val context: Context): RecyclerView.Adapter<UsersAdapter.ViewHolder>(){

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener)
    {
        mListener = listener
    }

    fun deleteItem(i: Int)
    {
        users.removeAt(i)
        notifyDataSetChanged()
    }


    fun addItem(i: Int, user: User)
    {
        users.add(i,user)
        notifyDataSetChanged()
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

    inner class ViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView){
        fun bind(user: User)
        {
            val ivFoto = itemView.findViewById<ImageView>(R.id.imageView)
            val tvNombre = itemView.findViewById<TextView>(R.id.tvTitulo)
            val tvCorreo = itemView.findViewById<TextView>(R.id.tvCorreo)

            tvNombre.text = user.name
            tvCorreo.text = user.email
            ivFoto.setOnClickListener {

                val builder = AlertDialog.Builder(context).create()
                val layoutInflater = LayoutInflater.from(context)
                val view: View = layoutInflater.inflate(R.layout.custom_dialog, null)

                val txtNombre = view.findViewById<TextView>(R.id.txtNombre)
                val txtEmail = view.findViewById<TextView>(R.id.txtEmail)
                val txtGender = view.findViewById<TextView>(R.id.txtGender)
                val txtStatuss = view.findViewById<TextView>(R.id.txtstatus)

                txtNombre.text = user.name
                txtEmail.text = user.email
                txtGender.text = user.gender
                txtStatuss.text = user.status

                builder.setView(view)
                builder.create()
                builder.show()
            }

        }
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }
    }
}