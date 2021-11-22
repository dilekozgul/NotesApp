package com.dilekozgul.notesapp.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.dilekozgul.notesapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
        window.statusBarColor = ContextCompat.getColor(this, R.color.statusBarColor)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFAC2553")))
     //   window.navigationBarColor = ContextCompat.getColor(this, R.color.navigationBarColor)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}