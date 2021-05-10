package com.example.acalculator.ui.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import com.example.acalculator.*
import com.example.acalculator.data.local.room.entities.Operation
import com.example.acalculator.ui.viewmodels.CalculatorViewModel
import com.example.acalculator.ui.listeners.OnDisplayChanged
import com.example.acalculator.ui.adapters.HistoryAdapter
import kotlinx.android.synthetic.main.fragment_calculator.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val EXTRA_HISTORY = "com.example.acalculator"

var operations = ArrayList<Operation>()

class CalculatorFragment : Fragment(),
    OnDisplayChanged {
    private val TAG = CalculatorFragment::class.java.simpleName
    private val VISOR_KEY = "visor"
    var pattern = "HH:mm:ss"

    //lateinit informa que esta variável sertá declarada em execução
    private lateinit var viewModel: CalculatorViewModel

    var simpleDateFormat: SimpleDateFormat = SimpleDateFormat(pattern)
    var horasFormatadas: String = simpleDateFormat.format(Date().time)
    val lista = mutableListOf(
        Operation("1+1", 2.0),
        Operation("2+3", 5.0)
    )
    var history: HistoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        val binding = ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.registerListener(this)

        /*button_equals.setOnClickListener {
            onClickEquals()
        }

        button_clear.setOnClickListener {
            onClear()
        }

        button_backspace.setOnClickListener {
            backspace()
        }*/

        /*button_history.setOnClickListener {
            onClickHistory(button_history)
        }*/

        val orientation = this.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

        } else {
            history = HistoryAdapter(
                activity as Context,
                R.layout.item_expression,
                lista as ArrayList<Operation>
                //ArrayList(viewModel.onShowList())
            )

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unregisterListener()
    }

    @OnClick(R.id.button_backspace)
    fun backspace() {
        Log.i(TAG, "Click no botão C")
        text_visor.text = viewModel.onClickClearLastSymbol()
    }

    @OnClick(R.id.button_clear)
    fun onClear() {
        Log.i(TAG, "Click no botão CE")
        text_visor.text = viewModel.onClickClearEverything()
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
    @OnClick(
        R.id.button_0,
        R.id.button_1,
        R.id.button_2,
        R.id.button_3,
        R.id.button_4,
        R.id.button_5,
        R.id.button_6,
        R.id.button_7,
        R.id.button_8,
        R.id.button_9,
        R.id.button_adition,
        R.id.button_subtraction,
        R.id.button_multiplication,
        R.id.button_division,
        R.id.button_decimal,
        R.id.button_doubleZero
    )
    fun onClickSymbol(view: View) {
        text_visor.text = viewModel.onClickSymbol(view.tag.toString())
    }

    @OnClick(R.id.button_equals)
    fun onClickEquals(view: View) {
        text_visor.text = viewModel.onClickEquals()
    }

    override fun onDisplayChanged(value: String?) {
        value.let { text_visor.text = it }
    }
}