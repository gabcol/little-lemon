package com.example.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuFromJason(
    @SerialName("menu")
    val menu: List<MenuItemFromJason>
)

@Serializable
data class MenuItemFromJason(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String,

    @SerialName("price")
    val price: Double,

    @SerialName("image")
    val image: String,

    @SerialName("category")
    val category: String,
)   {
    fun toMenuItemRoom() = MenuItemRoom(id, title, description, price, image, category)
}