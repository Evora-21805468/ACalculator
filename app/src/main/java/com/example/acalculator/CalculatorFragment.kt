package com .example.acalculator

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val EXTRA_HISTORY = "com.example.acalculator"

var operations = ArrayList<Operation>()

class CalculatorFragment : Fragment() {
    private val TAG = CalculatorFragment::class.java.simpleName
    private val VISOR_KEY = "visor"
    var pattern = "HH:mm:ss"

    var simpleDateFormat: SimpleDateFormat = SimpleDateFormat(pattern)
    var horasFormatadas: String = simpleDateFormat.format(Date().time)
    val lista = mutableListOf(Operation("1+1",2.0),Operation("2+3",5.0))
    var history: HistoryAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        val binding =
        ButterKnife.bind(this, view)
        return view
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

        /*button_history.setOnClickListener {
            onClickHistory(button_history)
        }*/

        val orientation = this.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

        } else {
            history = HistoryAdapter(activity as Context, R.layout.item_expression, lista as ArrayList<Operation>)

        }

    }

    @OnClick(R.id.button_backspace)
     fun backspace() {
        Log.i(TAG, "Click no botão C")
        var conta = text_visor.text.toString()
        if (conta.isEmpty()) {
            text_visor.text = "0"
        } else {
            text_visor.text = conta.substring(0, conta.length - 1)
        }
    }

    @OnClick(R.id.button_clear)
     fun onClear() {
        Log.i(TAG, "Click no botão CE")
        text_visor.text = ""
    }

    /*  fun checkHistory() {
         Log.i(TAG, "Click no botão HIST")
         if (lista.size >= 1) {
             text_visor.text = lista[lista.size - 1]
         }
     }*/

   /* @OnClick(R.id.button_history)
     fun onClickHistory(view: View){
        Log.i(TAG, "Click no botão HIST")
        val intent = Intent(this , HistoryActivity::class.java)
        intent.apply { putParcelableArrayListExtra(EXTRA_HISTORY, ArrayList(lista)) }
        startActivity(intent)
        finish()
    }*/

    @Optional
    @OnClick(R.id.button_0,R.id.button_1,R.id.button_2,R.id.button_3,R.id.button_4,R.id.button_5,
        R.id.button_6,R.id.button_7,R.id.button_8,R.id.button_9,R.id.button_adition,R.id.button_subtraction,
        R.id.button_multiplication,R.id.button_division,R.id.button_decimal,R.id.button_doubleZero)
    fun onClickSymbol(view: View) {
        val symbol = view.tag.toString()
        Log.i(TAG, "Click no botão $symbol")
        if (text_visor.text == "0") {
            text_visor.text = symbol
        } else {
            text_visor.append(symbol)
        }

    }

    @OnClick(R.id.button_equals)
     fun onClickEquals() {
        Log.i(TAG, "Click no botão =")
        try {
            val expression = ExpressionBuilder(text_visor.text.toString()).build()
            val conta = text_visor.text.toString()
            val resultado = expression.evaluate()
            text_visor.text = resultado.toString()
            operations.add(Operation(conta,resultado))
            history?.notifyDataSetChanged()

        } catch (e: Exception) {
            text_visor.text = "0"
        }
    }
}