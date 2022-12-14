package com.example.prac07

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<Button>(R.id.myButton).setOnClickListener {
            val intent = Intent(this, TwoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val menuItem1: MenuItem? = menu?.add(0,0,0,"menu1")
//        val menuItem2: MenuItem? = menu?.add(0,1,0,"menu2")
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d("kkang","onQueryTextChange")
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.d("kkang","onQueryTextSubmit" )
                return true
            }
        })
        val menuItem1 = menu?.findItem(R.id.menu1)
        menuItem1.setOnMenuItemClickListener {
            Log.d("kkang","menu1 click")
            true
        }

        return super.onCreateOptionsMenu(menu)
    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
//        R.id.menu1 ->{
//            Log.d("kkang","menu1 click")
//            true
//        }
//        R.id.menu2->{
//            Log.d("kkang", "menu2 click")
//            true
//        }
//        else -> super.onOptionsItemSelected(item)
//    }
}