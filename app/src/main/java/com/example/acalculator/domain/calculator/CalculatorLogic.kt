package com.example.acalculator.domain.calculator

import com.example.acalculator.data.local.list.ListStorage
import com.example.acalculator.data.local.room.dao.OperationDao
import com.example.acalculator.data.local.room.entities.Operation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorLogic (private val storage: OperationDao){

    suspend fun getListOperations(): List<Operation>{
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
            storage.insert(
                Operation(
                    expression,
                    result
                )
            )
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