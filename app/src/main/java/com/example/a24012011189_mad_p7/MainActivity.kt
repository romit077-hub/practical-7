package com.example.a24012011189_mad_p7

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PersonAdapter
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dbHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        tvError = findViewById(R.id.tvError)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val cachedPersons = dbHelper.getAllPersons()
        adapter = PersonAdapter(cachedPersons)
        recyclerView.adapter = adapter

        if (cachedPersons.isEmpty()) {
            tvError.text = getString(R.string.loading_data)
            tvError.visibility = View.VISIBLE
        }

        fetchAndDisplayPersons()
    }

    private fun fetchAndDisplayPersons() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val persons = HttpRequest.fetchPersons()
                persons.forEach { dbHelper.insertPerson(it) }
                val allPersons = dbHelper.getAllPersons()
                withContext(Dispatchers.Main) {
                    adapter.updateData(allPersons)
                    if (allPersons.isEmpty()) {
                        tvError.text = getString(R.string.no_persons_found)
                        tvError.visibility = View.VISIBLE
                    } else {
                        tvError.visibility = View.GONE
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    if (adapter.itemCount == 0) {
                        tvError.text = getString(R.string.error_prefix, e.localizedMessage ?: e.toString())
                        tvError.visibility = View.VISIBLE
                    } else {
                        Toast.makeText(this@MainActivity, getString(R.string.update_failed, e.localizedMessage), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
