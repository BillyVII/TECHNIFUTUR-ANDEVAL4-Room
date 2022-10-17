package com.technopixl.and14.evalroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.technopixl.and14.evalroom.adapter.ExpenseTypeAdapter
import com.technopixl.and14.evalroom.model.ExpenseType
import com.technopixl.and14.evalroom.model.ExpenseTypeModelFactory
import com.technopixl.and14.evalroom.model.ExpenseViewModel



class MainActivity : AppCompatActivity() {

    private val newExpenseTypeActivityRequestCode = 1
    private val expenseTypeViewModel: ExpenseViewModel by viewModels {
        ExpenseTypeModelFactory((application as ExpenseTypeApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ExpenseTypeAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)

        expenseTypeViewModel.allExpenseType.observe(this){ expenseType ->
            expenseType.let { adapter.submitList(it) }
        }
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewExpenseTypeActivity::class.java)
            startActivityForResult(intent, newExpenseTypeActivityRequestCode)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentdata: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentdata)

        if (requestCode == newExpenseTypeActivityRequestCode && resultCode == RESULT_OK) {
            intentdata?.getStringExtra(NewExpenseTypeActivity.EXTRA_REPLY)?.let { reply ->
                val expenseType = ExpenseType(reply)
              expenseTypeViewModel.insert(expenseType)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "ExpenseType not saved because it is empty",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}