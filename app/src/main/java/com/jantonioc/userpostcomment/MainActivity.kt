package com.jantonioc.userpostcomment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jantonioc.userpostcomment.data.UsersAdapter
import com.jantonioc.userpostcomment.dto.User
import com.jantonioc.userpostcomment.dto.UserResponse
import com.jantonioc.userpostcomment.network.ApiAdapter
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var progressBar: ProgressBar
    private lateinit var myRecyclerView: RecyclerView
    private var adapter:UsersAdapter?= null
    private var usersList:List<User> = ArrayList()
    private var layoutManager: RecyclerView.LayoutManager?= null


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
                try{
                    val apiResponse = ApiAdapter.apiClient.getListOfUser()
                    if (apiResponse.data.isNotEmpty()) {
                        progressBar.visibility = View.INVISIBLE
                        val users = apiResponse
                        initRecycler(users)
                        Log.v("APIDATA", "Data: ${users}")
                    }else{
                        progressBar.visibility = View.INVISIBLE
                        Log.v("APIDATA", "No se encontro usuarios")
                    }
                }catch (e: Exception){
                    progressBar.visibility = View.INVISIBLE
                    Log.v("APIDATA", "Exception: ${e.localizedMessage}")
                }
            }
    }

    private fun initRecycler(users: UserResponse) {
        usersList = users.data

        layoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = layoutManager

        adapter = UsersAdapter(usersList, this)
        myRecyclerView.adapter = adapter
        adapter!!.setOnItemClickListener(object :UsersAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                var i = Intent(this@MainActivity,UserPostActivity::class.java)
                i.putExtra("ID", usersList[position].id)
                i.putExtra("NOMBRE",usersList[position].name)
                startActivity(i)
            }

        })

    }
}
