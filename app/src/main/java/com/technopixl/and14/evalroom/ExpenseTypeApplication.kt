package com.technopixl.and14.evalroom

import android.app.Application
import com.technopixl.and14.evalroom.db.ExpenseTypeDatabase
import com.technopixl.and14.evalroom.db.ExpenseTypeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ExpenseTypeApplication : Application() {

   private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ExpenseTypeDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { ExpenseTypeRepository(database.expenseTypeDao()) }

}