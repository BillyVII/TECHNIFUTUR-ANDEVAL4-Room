package com.technopixl.and14.evalroom.db

import androidx.annotation.WorkerThread
import com.technopixl.and14.evalroom.dao.ExpenseTypeDao
import com.technopixl.and14.evalroom.model.ExpenseType
import kotlinx.coroutines.flow.Flow

class ExpenseTypeRepository(private val expenseTypeDao: ExpenseTypeDao) {
    val allExpenseType: Flow<List<ExpenseType>> = expenseTypeDao.getExpenseType()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(expenseType: ExpenseType) {
        expenseTypeDao.insert(expenseType)
    }


}