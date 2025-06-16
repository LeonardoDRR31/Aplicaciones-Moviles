package com.example.diarioelperuano

sealed class MenuItem {
    data class Simple(val title: String, val url: String) : MenuItem()
    data class WithSubItems(val title: String, val subItems: List<Simple>) : MenuItem()
}