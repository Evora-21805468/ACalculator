package com.example.acalculator.domain.history

import com.example.acalculator.data.local.list.ListStorage
import com.example.acalculator.data.local.room.entities.Operation

class HistoryLogic {

    private val storage =
        ListStorage.getInstance()

    fun getAllOperations(): List<Operation>{
        return storage.getAll()
    }

    fun removeFromList(pos: Int){
        storage.remove(pos)
    }
}