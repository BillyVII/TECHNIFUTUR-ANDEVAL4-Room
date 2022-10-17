package com.technopixl.and14.evalroom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.technopixl.and14.evalroom.model.ExpenseType
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseTypeDao {
    @Query("SELECT * FROM expenseType_table")
    fun getExpenseType(): Flow<List<ExpenseType>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expenseType:ExpenseType)

    @Query("DELETE FROM expenseType_table")
    suspend fun deleteAll()
}