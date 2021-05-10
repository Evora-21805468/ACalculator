package com.example.acalculator.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.acalculator.domain.history.HistoryLogic
import com.example.acalculator.data.local.room.entities.Operation
import com.example.acalculator.ui.listeners.OnListChanged

class HistoryViewModel: ViewModel() {
    private val historicLogic =
        HistoryLogic()
    private var listener: OnListChanged? = null

    private fun notifyOnListChanged(){
        listener?.onListChanged(historicLogic.getAllOperations())
    }

    fun registerListener(listener: OnListChanged){
        this.listener = listener
        listener.onListChanged(historicLogic.getAllOperations())
    }

    fun unregisterListener(){
        listener = null
    }

    fun onCreateList(): List<Operation>{
        return historicLogic.getAllOperations()
    }


}