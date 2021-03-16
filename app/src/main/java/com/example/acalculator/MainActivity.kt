package com.example.acalculator

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val VISOR_KEY = "visor"
    var pattern = "HH:mm:ss"

    @SuppressLint("SimpleDateFormat")
    var simpleDateFormat: SimpleDateFormat = SimpleDateFormat(pattern)
    var horasFormatadas: String = simpleDateFormat.format(Date().time)
    var history: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "O método onCreate foi invocado")

    }

    override fun onDestroy() {
        Log.i(TAG, "O método onDestroy foi invocado")
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        text_visor.text = savedInstanceState?.getString(VISOR_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putString(VISOR_KEY, text_visor.text.toString()) }
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()

        button_1.setOnClickListener {
            onClickSymbol("1")
        }

        button_2.setOnClickListener {
            onClickSymbol("2")
        }

        button_3.setOnClickListener {
            onClickSymbol("3")
        }

        button_4.setOnClickListener {
            onClickSymbol("4")
        }

        button_5.setOnClickListener {
            onClickSymbol("5")
        }

        button_6.setOnClickListener {
            onClickSymbol("6")
        }

        button_7.setOnClickListener {
            onClickSymbol("7")
        }

        button_8.setOnClickListener {
            onClickSymbol("8")
        }

        button_9.setOnClickListener {
            onClickSymbol("9")
        }

        button_0.setOnClickListener {
            onClickSymbol("0")
        }

        button_doubleZero.setOnClickListener {
            onClickSymbol("00")
        }

        button_decimal.setOnClickListener {
            onClickSymbol(".")
        }

        button_adition.setOnClickListener {
            onClickSymbol("+")
        }

        button_subtraction.setOnClickListener {
            onClickSymbol("-")
        }

        button_multiplication.setOnClickListener {
            onClickSymbol("*")
        }

        button_division.setOnClickListener {
            onClickSymbol("/")
        }

        button_equals.setOnClickListener {
            onClickEquals()
        }

        button_clear.setOnClickListener {
            onClear()
        }

        button_backspace.setOnClickListener {
            backspace()
        }

        button_history.setOnClickListener {
            checkHistory()
        }

        val configuration: Configuration = resources.configuration
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            list_history.adapter = HistoryAdapter(this, R.layout.item_expression, arrayListOf("1+1=2", "2+3=5"))
        }

    }

    private fun backspace() {
        Log.i(TAG, "Click no botão C")
        var conta = text_visor.text.toString()
        if (conta.isEmpty()) {
            text_visor.text = "0"
        } else {
            text_visor.text = conta.substring(0, conta.length - 1)
        }
    }

    private fun onClear() {
        Log.i(TAG, "Click no botão CE")
        text_visor.text = ""
    }

    private fun checkHistory() {
        Log.i(TAG, "Click no botão HIST")
        if (history.size >= 1) {
            text_visor.text = history[history.size - 1]
        }
    }

    private fun onClickSymbol(symbol: String) {
        Log.i(TAG, "Click no botão $symbol")

        if (text_visor.text == "0") {
            text_visor.text = symbol
        } else {
            text_visor.append(symbol)
        }

    }

    private fun onClickEquals() {
        Log.i(TAG, "Click no botão =")
        try {

            val expression = ExpressionBuilder(text_visor.text.toString()).build()
            val resultado = expression.evaluate()
            text_visor.text = resultado.toString()
            history.add(resultado.toString())
            Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
            Toast.makeText(
                this,
                "button_equals.setOnClickListener $horasFormatadas",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
            text_visor.text = "0"
        }
    }
}
