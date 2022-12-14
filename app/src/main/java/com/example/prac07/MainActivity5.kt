package com.example.prac07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.prac07.databinding.ActivityMain5Binding

class MainActivity5 : AppCompatActivity() {
    val binding5 by lazy{
        ActivityMain5Binding.inflate(layoutInflater)
    }
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main5)
        setContentView(binding5.root)

        toggle = ActionBarDrawerToggle(this, binding5.drawer, R.string.drawer_opened,
        R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}