package io.devexpert.nav3samples.data

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String = ""
)

val products = listOf(
    Product(1, "Smartphone X", 799.99, "A very generic smartphone"),
    Product(2, "Laptop Pro", 1299.99, "Powerful laptop for professionals"),
    Product(3, "Wireless Earbuds", 199.99, "In-ear headphones with noise cancellation"),
    Product(4, "Smartwatch 4", 299.99, "Smartwatch with heart rate monitoring"),
    Product(5, "Tablet S7", 499.99, "Slim and light tablet for everyday use"),
)