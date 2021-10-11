package com.jantonioc.userpostcomment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jantonioc.userpostcomment.data.PostsAdapter
import com.jantonioc.userpostcomment.data.UsersAdapter
import com.jantonioc.userpostcomment.dto.Post
import com.jantonioc.userpostcomment.dto.PostResponse
import com.jantonioc.userpostcomment.dto.User
import com.jantonioc.userpostcomment.dto.UserResponse
import com.jantonioc.userpostcomment.network.ApiAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class UserPostActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var tvUser:TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var myRecyclerView: RecyclerView
    private var adapter: PostsAdapter?= null
    private var postList:List<Post> = ArrayList()
    private var layoutManager: RecyclerView.LayoutManager?= null
    private var idUser: Int = 0
    private var nameUSer:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_post)

        val actionBar = supportActionBar
        actionBar!!.title = "Posts User"
        actionBar.setDisplayHomeAsUpEnabled(true)

        idUser = intent.getIntExtra("ID",0)
        nameUSer = intent.getStringExtra("NOMBRE").toString()

        tvUser = findViewById(R.id.tvNombre)
        tvUser.text = nameUSer
        progressBar = findViewById(R.id.progressBar)
        //progressBar.visibility = View.INVISIBLE
        myRecyclerView = findViewById(R.id.myRecyclerView)
        loadData()
    }

    private fun loadData() {
        launch()
        {
            try{
                val apiResponse = ApiAdapter.apiClient.getPostUser(idUser)
                if (apiResponse.data.isNotEmpty()) {
                    progressBar.visibility = View.INVISIBLE
                    val posts = apiResponse
                    initRecycler(posts)
                    Log.v("APIDATA", "Data: ${posts}")
                }else{
                    progressBar.visibility = View.INVISIBLE
                    Log.v("APIDATA", "No se encontro usuarios")
                    Toast.makeText(this@UserPostActivity,"Este Usuario no posee publicaciones",Toast.LENGTH_SHORT).show()
                    onBackPressed()
                }
            }catch (e: Exception){
                progressBar.visibility = View.INVISIBLE
                Log.v("APIDATA", "Exception: ${e.localizedMessage}")
            }
        }
    }

    private fun initRecycler(posts: PostResponse) {
        postList = posts.data

        layoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = layoutManager

        adapter = PostsAdapter(postList, this)
        myRecyclerView.adapter = adapter
        adapter!!.setOnItemClickListener(object :PostsAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@UserPostActivity,"XD",Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}