package com.example.acalculator


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val EXTRA_HISTORY = "com.example.acalculator"

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val VISOR_KEY = "visor"
    var pattern = "HH:mm:ss"

    var simpleDateFormat: SimpleDateFormat = SimpleDateFormat(pattern)
    var horasFormatadas: String = simpleDateFormat.format(Date().time)
    val lista = mutableListOf(Operation("1+1",2.0),Operation("2+3",5.0))
    var history: HistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "O método onCreate foi invocado")

    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume ${horasFormatadas}", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause ${horasFormatadas}", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop ${horasFormatadas}", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart ${horasFormatadas}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy ${horasFormatadas}", Toast.LENGTH_SHORT).show()
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
            onClickHistory(button_history)
        }

        val orientation = this.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

        } else {
            history = HistoryAdapter(this, R.layout.item_expression, lista as ArrayList<Operation>)

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

   /* private fun checkHistory() {
        Log.i(TAG, "Click no botão HIST")
        if (lista.size >= 1) {
            text_visor.text = lista[lista.size - 1]
        }
    }*/

    private fun onClickHistory(view: View){
        Log.i(TAG, "Click no botão HIST")
        val intent = Intent(this , HistoryActivity::class.java)
        intent.apply { putParcelableArrayListExtra(EXTRA_HISTORY, ArrayList(lista)) }
        startActivity(intent)
        finish()
    }

    fun onClickSymbol(view: View) {
        val symbol = view.tag.toString()
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
            val conta = text_visor.text.toString()
            val resultado = expression.evaluate()
            text_visor.text = resultado.toString()
            lista.add(Operation(conta,resultado))
            val orientation = this.resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                // code for portrait mode
            } else {
                history?.notifyDataSetChanged()
            }

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
