package com.technopixl.and14.evalroom.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.technopixl.and14.evalroom.R
import com.technopixl.and14.evalroom.model.ExpenseType
import org.w3c.dom.Text


class ExpenseTypeAdapter : ListAdapter<ExpenseType, ExpenseTypeAdapter.ExpenseTypeViewHolder>(WordsComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseTypeViewHolder {
        return ExpenseTypeViewHolder.create(parent)

    }

    override fun onBindViewHolder(holder: ExpenseTypeViewHolder, position: Int) {
        val expenses = getItem(position)
        holder.bind(expenses.name)
    }

    class ExpenseTypeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private var expenseTypeItemView: TextView = itemView.findViewById(R.id.textView)
        fun bind(text: String) {
            expenseTypeItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup):ExpenseTypeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return ExpenseTypeViewHolder(view)
            }
        }
    }
    class WordsComparator : DiffUtil.ItemCallback<ExpenseType>() {
        override fun areItemsTheSame(oldItem: ExpenseType, newItem: ExpenseType): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ExpenseType, newItem: ExpenseType): Boolean {
            return oldItem.name == newItem.name
        }
    }


}