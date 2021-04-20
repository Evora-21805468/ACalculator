package com.example.acalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class NavigationManager {
    companion object {
        private fun placeFragment(fm: FragmentManager, fragment: Fragment, bundle: Bundle?){
            val transition = fm.beginTransaction()
            fragment.arguments = bundle
            transition.replace(R.id.frame, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }


        fun goToCalculatorFragment(fm: FragmentManager, bundle: Bundle?){
            placeFragment(fm, CalculatorFragment(), bundle)

        }

        fun goToHistoryFragment(fm: FragmentManager, bundle: Bundle?){
            placeFragment(fm, HistoryFragment(), bundle)

        }
    }
}