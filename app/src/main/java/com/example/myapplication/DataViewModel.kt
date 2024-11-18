package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel: ViewModel() {
    private val _data = MutableLiveData<Data>()
    val data: LiveData<Data> = _data

    fun initData() {
        _data.value = Data("Euro", "Dollar", 0.0, 0.0)
    }

    fun updateData(newData: Data) {
        _data.value?.let { oldData ->
            val updateData = oldData.copy(
                newData.labelHaut ?: oldData.labelHaut,
                newData.labelBas ?: oldData.labelBas,
                newData.resultatHaut ?: oldData.resultatHaut,
                newData.resultatBas ?: oldData.resultatBas,
            )
            _data.value = updateData
        }
    }
}
