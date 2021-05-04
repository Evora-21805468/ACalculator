package com.example.acalculator

import androidx.lifecycle.ViewModel

class HistoryViewModel: ViewModel() {
    private val historicLogic = HistoryLogic()
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