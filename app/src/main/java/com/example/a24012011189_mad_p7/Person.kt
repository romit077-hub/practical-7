package com.example.a24012011189_mad_p7

import java.io.Serializable

data class Person(
    val id: String,
    val name: String,
    val emailId: String,
    val phoneNo: String,
    val address: String,
    val latitude: Double,
    val longitude: Double
) : Serializable
