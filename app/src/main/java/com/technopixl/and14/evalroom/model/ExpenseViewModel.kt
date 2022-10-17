package com.technopixl.and14.evalroom.model

import androidx.lifecycle.*
import com.technopixl.and14.evalroom.db.ExpenseTypeRepository
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: ExpenseTypeRepository):ViewModel() {
    val allExpenseType: LiveData<List<ExpenseType>> = repository.allExpenseType.asLiveData()

    fun insert(expenseType: ExpenseType) = viewModelScope.launch {
        repository.insert(expenseType)
    }
}
class ExpenseTypeModelFactory(private val repository: ExpenseTypeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>):T {
        if(modelClass.isAssignableFrom(ExpenseViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ExpenseViewModel(repository) as T
        }
    throw IllegalArgumentException("Unknow ViewModel class")
    }

}