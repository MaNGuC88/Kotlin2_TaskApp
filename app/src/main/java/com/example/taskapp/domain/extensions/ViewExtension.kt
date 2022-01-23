package com.example.taskapp.domain.extensions

import androidx.recyclerview.widget.RecyclerView

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(absoluteAdapterPosition, itemViewType)
    }
    return this
}


