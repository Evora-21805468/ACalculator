package com.example.acalculator.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.acalculator.data.local.room.CalculatorDatabase
import com.example.acalculator.domain.calculator.CalculatorLogic
import com.example.acalculator.data.local.room.entities.Operation
import com.example.acalculator.ui.listeners.OnDisplayChanged

class CalculatorViewModel(application: Application) : AndroidViewModel(application) {

    private val storage = CalculatorDatabase.getInstance(application).operationDao()
    private val calculatorLogic = CalculatorLogic(storage)

    var display: String = ""
    private var listener: OnDisplayChanged? = null

    private fun notifyOnDisplayChanged(){
        listener?.onDisplayChanged(display)
    }

    fun registerListener(listener: OnDisplayChanged){
        this.listener = listener
        listener?.onDisplayChanged(display)
    }

    fun unregisterListener(){
        listener = null
    }

    suspend fun onShowList(): List<Operation>{
        return calculatorLogic.getListOperations()
    }

    fun onClickSymbol(symbol: String): String {
        display = calculatorLogic.insertSymbol(display, symbol)
        notifyOnDisplayChanged()
        return display
    }

    fun onClickEquals(): String {
        val result = calculatorLogic.performOperation(display)
        display = result.toString()
        notifyOnDisplayChanged()
        return display
    }

    fun onClickClearEverything(): String {
        display = calculatorLogic.clearEverything()
        notifyOnDisplayChanged()
        return display
    }

    fun onClickClearLastSymbol(): String{
        display = calculatorLogic.clearLastSymbol(display)
        notifyOnDisplayChanged()
        return display
    }
}