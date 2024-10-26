package com.app.roomdbapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.room.InvalidationTracker
import androidx.room.Room
import com.app.roomdbapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var database: ContactDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        database = Room.databaseBuilder(applicationContext, ContactDB::class.java, "contactDB").build()

        database = ContactDB.getDatabase(this)
        var database2 = ContactDB.getDatabase(this)

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0, "Dharmin", "9328356264", Date(), 1))
        }
    }

    fun getData(view: View) {
        database.contactDao().getContacts().observe(this, Observer {
            Log.d("Dharmin", it.toString())
        })
    }
}