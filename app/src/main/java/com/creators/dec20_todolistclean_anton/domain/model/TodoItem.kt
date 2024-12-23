package com.creators.dec20_todolistclean_anton.domain.model

data class TodoItem(
    val id: Int = 0,
    val title: String,
    val description: String,
    val deadline: Long
) {
}