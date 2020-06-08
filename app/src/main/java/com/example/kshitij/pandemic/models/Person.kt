package com.example.kshitij.pandemic.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "covid_table")
data class Person(

    val firstName: String,
    val lastName: String,
    val age: String
): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}