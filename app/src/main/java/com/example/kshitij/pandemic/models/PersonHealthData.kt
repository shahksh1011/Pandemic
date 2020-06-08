package com.example.kshitij.pandemic.models


import androidx.room.*
import java.io.Serializable
import java.sql.Date
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


@Entity(tableName = "covid_health_entry_table",
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["id"],
            childColumns = ["personId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE)
    ]
)
data class PersonHealthData (

    val bodyTemperature: String,
    val oxygenLevel: String,
    val personId: Int,
    val dateEntered: String
//    val date: java.util.Date?=null

): Serializable{
    @PrimaryKey(autoGenerate = true)
    var recordId: Int = 0
}
