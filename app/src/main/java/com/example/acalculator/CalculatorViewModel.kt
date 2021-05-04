package com.example.acalculator

import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private val calculatorLogic = CalculatorLogic()
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

    fun onShowList(): List<Operation>{
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