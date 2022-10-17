package com.technopixl.and14.evalroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenseType_table")
data class ExpenseType(
    @PrimaryKey @ColumnInfo(name = "name")
    var name: String
)
