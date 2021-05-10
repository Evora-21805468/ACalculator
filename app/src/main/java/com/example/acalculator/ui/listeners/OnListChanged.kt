package com.example.acalculator.ui.listeners

import com.example.acalculator.data.local.room.entities.Operation

interface OnListChanged {
    fun onListChanged(value: List<Operation>)
}