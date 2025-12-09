package com.example.catgallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, CatListFragment())
                .commit()
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_cats -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, CatListFragment())
                        .commit()
                    true
                }
                R.id.nav_videos -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, CatMainFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}
