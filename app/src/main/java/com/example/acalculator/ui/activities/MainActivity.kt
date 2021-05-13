package com.example.acalculator.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.acalculator.R
import com.example.acalculator.ui.utils.NavigationManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        if(!screenRotated(savedInstanceState)){
            NavigationManager.goToCalculatorFragment(
                supportFragmentManager
            )
        }
    }

    private fun screenRotated(savedInstanceState: Bundle?) : Boolean{
        return savedInstanceState != null
    }

    private fun setupDrawerMenu(){
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        nav_drawer.setNavigationItemSelectedListener(this)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

     override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_calculator -> NavigationManager.goToCalculatorFragment(
                supportFragmentManager
            )
            R.id.nav_history -> NavigationManager.goToHistoryFragment(
                supportFragmentManager
            )
            R.id.nav_login -> NavigationManager.goToLoginFragment(
                supportFragmentManager
            )
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else if(supportFragmentManager.backStackEntryCount==1){
            finish()
        }else{
            super.onBackPressed()
        }
    }
}
