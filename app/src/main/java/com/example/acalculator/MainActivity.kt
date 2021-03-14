package com.example.acalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    var pattern = "HH:mm:ss"

    @SuppressLint("SimpleDateFormat")
    var simpleDateFormat: SimpleDateFormat = SimpleDateFormat(pattern)
    var horasFormatadas: String = simpleDateFormat.format(Date().time)
    var history: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        button_1.setOnClickListener {
            Log.i(TAG, "Click no botão 1")
            if (text_visor.text == "0") {
                text_visor.text = "1"
            } else {
                text_visor.append("1")
            }
            Toast.makeText(this, "button_1.setOnClickListener $horasFormatadas", Toast.LENGTH_SHORT)
                .show()
        }

        button_2.setOnClickListener {
            Log.i(TAG, "Click no botão 2")
            if (text_visor.text == "0") {
                text_visor.text = "2"
            } else {
                text_visor.append("2")
            }
            Toast.makeText(this, "button_2.setOnClickListener $horasFormatadas", Toast.LENGTH_SHORT)
                .show()
        }

        button_3.setOnClickListener {
            Log.i(TAG, "Click no botão 3")
            if (text_visor.text == "0") {
                text_visor.text = "3"
            } else {
                text_visor.append("3")
            }
            Toast.makeText(this, "button_3.setOnClickListener $horasFormatadas", Toast.LENGTH_SHORT)
                .show()
        }

        button_4.setOnClickListener {
            Log.i(TAG, "Click no botão 4")
            if (text_visor.text == "0") {
                text_visor.text = "4"
            } else {
                text_visor.append("4")
            }
            Toast.makeText(this, "button_4.setOnClickListener $horasFormatadas", Toast.LENGTH_SHORT)
                .show()
        }

        button_5.setOnClickListener {
            Log.i(TAG, "Click no botão 5")
            if (text_visor.text == "0") {
                text_visor.text = "5"
            } else {
                text_visor.append("5")
            }
            Toast.makeText(this, "button_5.setOnClickListener $horasFormatadas", Toast.LENGTH_SHORT)
                .show()
        }

        button_6.setOnClickListener {
            Log.i(TAG, "Click no botão 6")
            if (text_visor.text == "0") {
                text_visor.text = "6"
            } else {
                text_visor.append("6")
            }
            Toast.makeText(this, "button_6.setOnClickListener $horasFormatadas", Toast.LENGTH_SHORT)
                .show()
        }

        button_7.setOnClickListener {
            Log.i(TAG, "Click no botão 7")
            if (text_visor.text == "0") {
                text_visor.text = "7"
            } else {
                text_visor.append("7")
            }
            Toast.makeText(this, "button_7.setOnClickListener $horasFormatadas", Toast.LENGTH_SHORT)
                .show()
        }

        button_8.setOnClickListener {
            Log.i(TAG, "Click no botão 8")
            if (text_visor.text == "0") {
                text_visor.text = "8"
            } else {
                text_visor.append("8")
            }
            Toast.makeText(this, "button_8.setOnClickListener $horasFormatadas", Toast.LENGTH_SHORT)
                .show()
        }

        button_9.setOnClickListener {
            Log.i(TAG, "Click no botão 9")
            if (text_visor.text == "0") {
                text_visor.text = "9"
            } else {
                text_visor.append("9")
            }
            Toast.makeText(this, "button_9.setOnClickListener $horasFormatadas", Toast.LENGTH_SHORT)
                .show()
        }

        button_0.setOnClickListener {
            Log.i(TAG, "Click no botão 0")
            if (text_visor.text == "0") {
                text_visor.text = "0"
            } else {
                text_visor.append("0")
            }
            Toast.makeText(this, "button_0.setOnClickListener $horasFormatadas", Toast.LENGTH_SHORT)
                .show()
        }

        button_decimal.setOnClickListener {
            Log.i(TAG, "Click no botão .")
            text_visor.append(".")
            Toast.makeText(
                this,
                "button_decimal.setOnClickListener $horasFormatadas",
                Toast.LENGTH_SHORT
            ).show()
        }

        button_adition.setOnClickListener {
            Log.i(TAG, "Click no botão +")
            text_visor.append("+")
            Toast.makeText(
                this,
                "button_adition.setOnClickListener $horasFormatadas",
                Toast.LENGTH_SHORT
            ).show()
        }

        button_subtraction.setOnClickListener {
            Log.i(TAG, "Click no botão +")
            text_visor.append("-")
            Toast.makeText(
                this,
                "button_subtraction.setOnClickListener $horasFormatadas",
                Toast.LENGTH_SHORT
            ).show()
        }

        button_multiplication.setOnClickListener {
            Log.i(TAG, "Click no botão *")
            text_visor.append("*")
            Toast.makeText(
                this,
                "button_multiplication.setOnClickListener $horasFormatadas",
                Toast.LENGTH_SHORT
            ).show()
        }

        button_division.setOnClickListener {
            Log.i(TAG, "Click no botão /")
            text_visor.append("/")
            Toast.makeText(
                this,
                "button_division.setOnClickListener $horasFormatadas",
                Toast.LENGTH_SHORT
            ).show()
        }

        button_equals.setOnClickListener {
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

        button_clear.setOnClickListener {
            Log.i(TAG, "Click no botão C")
            text_visor.text = ""
            Toast.makeText(
                this,
                "button_clear.setOnClickListener $horasFormatadas",
                Toast.LENGTH_SHORT
            ).show()
        }

        button_history.setOnClickListener {
            Log.i(TAG, "Click no botão Hist")
            if (history.size >= 1) {
                text_visor.text = history[history.size - 1]
            }
            Toast.makeText(
                this,
                "button_history.setOnClickListener $horasFormatadas",
                Toast.LENGTH_SHORT
            ).show()
        }


    }
}
