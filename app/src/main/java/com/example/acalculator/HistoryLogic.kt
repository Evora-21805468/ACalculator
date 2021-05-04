package com.example.acalculator

class HistoryLogic {

    private val storage = ListStorage.getInstance()

    fun getAllOperations(): List<Operation>{
        return storage.getAll()
    }

    fun removeFromList(pos: Int){
        storage.remove(pos)
    }
}