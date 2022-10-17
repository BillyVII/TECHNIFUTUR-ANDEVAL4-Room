package com.technopixl.and14.evalroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class NewExpenseTypeActivity : AppCompatActivity() {

    private lateinit var editExpenseTypeView: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_expense_type)
        editExpenseTypeView = findViewById(R.id.editTextTextExpenseType)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editExpenseTypeView.text)) {
                setResult(Activity.RESULT_OK, replyIntent)
            } else {
                val expenseType = editExpenseTypeView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, expenseType)
            }
        }
    }
    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}