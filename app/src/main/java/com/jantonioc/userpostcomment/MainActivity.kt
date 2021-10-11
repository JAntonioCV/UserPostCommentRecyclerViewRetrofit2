package com.jantonioc.userpostcomment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jantonioc.userpostcomment.data.UsersAdapter
import com.jantonioc.userpostcomment.dto.User
import com.jantonioc.userpostcomment.dto.UserResponse
import com.jantonioc.userpostcomment.network.ApiAdapter
import kotlinx.coroutines.*
import androidx.recyclerview.widget.ItemTouchHelper as ItemTouchHelper

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var progressBar: ProgressBar
    private lateinit var myRecyclerView: RecyclerView
    private var adapter: UsersAdapter? = null
    private var usersList: ArrayList<User> = ArrayList()
    private var layoutManager: RecyclerView.LayoutManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        //progressBar.visibility = View.INVISIBLE
        myRecyclerView = findViewById(R.id.myRecyclerView)
        loadData()

    }

    private fun loadData() {
        launch()
        {
            try {
                val apiResponse = ApiAdapter.apiClient.getListOfUser()
                if (apiResponse.data.isNotEmpty()) {
                    progressBar.visibility = View.INVISIBLE
                    val users = apiResponse
                    initRecycler(users)
                    Log.v("APIDATA", "Data: ${users}")
                } else {
                    progressBar.visibility = View.INVISIBLE
                    Log.v("APIDATA", "No se encontro usuarios")
                }
            } catch (e: Exception) {
                progressBar.visibility = View.INVISIBLE
                Log.v("APIDATA", "Exception: ${e.localizedMessage}")
            }
        }
    }

    private fun initRecycler(users: UserResponse) {
        usersList = (ArrayList(users.data))

        layoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = layoutManager

        val swipegesture = object : SwipeGesture() {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        val alertDialog = AlertDialog.Builder(this@MainActivity)

                        alertDialog.apply {
                            setIcon(R.drawable.ic_baseline_info_24)
                            setTitle("Advertencia")
                            setMessage("Desea eliminar este registro de la lista")
                            setPositiveButton("Aceptar") { _, _ ->
                                Toast.makeText(this@MainActivity,"Eliminado",Toast.LENGTH_SHORT).show()
                                adapter?.deleteItem(viewHolder.adapterPosition)
                            }
                            setNegativeButton("Cancelar") { _, _ ->
                                adapter?.notifyItemChanged(viewHolder.adapterPosition)
                            }
                        }.create().show()

                    }
                    ItemTouchHelper.RIGHT -> {
                        val archiveItem = usersList[viewHolder.adapterPosition]
                        adapter?.deleteItem(viewHolder.adapterPosition)
                        adapter?.addItem(usersList.size, archiveItem)
                    }
                }
            }

        }

        val touchHelper = ItemTouchHelper(swipegesture)
        touchHelper.attachToRecyclerView(myRecyclerView)

        adapter = UsersAdapter(usersList, this)
        myRecyclerView.adapter = adapter
        adapter!!.setOnItemClickListener(object : UsersAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                var i = Intent(this@MainActivity, UserPostActivity::class.java)
                i.putExtra("ID", usersList[position].id)
                i.putExtra("NOMBRE", usersList[position].name)
                startActivity(i)
            }

        })

    }
}
