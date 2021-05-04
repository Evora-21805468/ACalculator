package com.example.acalculator

import android.util.Log
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorLogic {

    private val storage = ListStorage.getInstance()

    fun getListOperations(): List<Operation>{
        return storage.getAll()
    }

    fun insertSymbol(display: String, symbol: String): String{
        var displayAux = display
        if(display == "0") {
            displayAux = symbol
        } else {
            displayAux += symbol
        }
        return displayAux
    }

    fun performOperation(expression: String): Double{
        val expressionBuilder = ExpressionBuilder(expression).build()
        val result = expressionBuilder.evaluate()
        CoroutineScope(Dispatchers.IO).launch {
            storage.insert(Operation(expression, result))
        }
        return result
    }

    fun clearEverything(): String{
        return ""
    }

    fun clearLastSymbol(display: String): String{
        var displayAux = display
        if (!display.isEmpty()) {
            displayAux = display.substring(0, display.length - 1);
        }
        return displayAux
    }
}