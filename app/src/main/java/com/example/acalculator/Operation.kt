package com.example.acalculator

import java.util.*

class Operation(val expression: String, val result: Double){
    var uuid: String = UUID.randomUUID().toString()
}