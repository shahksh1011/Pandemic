package com.example.kshitij.pandemic


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kshitij.pandemic.models.Person
import com.example.kshitij.pandemic.models.PersonHealthData

@Dao
interface PersonDao {
    @Query("SELECT * from covid_table ORDER BY firstName ASC")
    fun getAllPeople():LiveData<List<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(person: Person)

    @Query("DELETE FROM covid_table")
    fun deleteAll()
}

@Dao
interface PersonHealthDao{
    @Query
        ("SELECT * from covid_health_entry_table WHERE personId LIKE :personId")
    fun getAllHealthData(personId: Int):LiveData<List<PersonHealthData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(personHealthData: PersonHealthData)

    @Query("DELETE FROM covid_health_entry_table")
    fun deleteAll()
}
