package com.example.belajarassesment1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController=this.findNavController(R.id.fragmentX)

        NavigationUI.setupActionBarWithNavController(this,navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=this.findNavController(R.id.fragmentX)
        return navController.navigateUp()
    }
}
