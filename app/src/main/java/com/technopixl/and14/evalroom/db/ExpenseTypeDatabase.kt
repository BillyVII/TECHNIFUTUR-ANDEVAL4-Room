package com.technopixl.and14.evalroom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.technopixl.and14.evalroom.dao.ExpenseTypeDao
import com.technopixl.and14.evalroom.model.ExpenseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(ExpenseType::class), version = 1, exportSchema = false)
abstract class ExpenseTypeDatabase : RoomDatabase() {
    abstract fun expenseTypeDao(): ExpenseTypeDao

    private class ExpenseTypeDatabaseCallback(
        private val scope: CoroutineScope
        ) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase){
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.expenseTypeDao())
                }
            }
        }
        suspend fun populateDatabase(expenseTypeDao: ExpenseTypeDao){
            // delete all content here
            expenseTypeDao.deleteAll()

            // add sample words
            var expenseType = ExpenseType("TAX")
            expenseTypeDao.insert(expenseType)
            expenseType = ExpenseType("LOISIR")
            expenseTypeDao.insert(expenseType)

        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ExpenseTypeDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ExpenseTypeDatabase {
            return (INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseTypeDatabase::class.java,
                    "expenseType_database"
                ).build()
                INSTANCE = instance
                instance
            })
        }
    }


}