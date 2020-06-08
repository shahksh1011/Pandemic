package com.example.kshitij.pandemic.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.kshitij.pandemic.PersonDao
import com.example.kshitij.pandemic.models.Person

class PersonRepository(private val personDao: PersonDao) {
    val allPeople: LiveData<List<Person>> = personDao.getAllPeople()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(person: Person){
        personDao.insert(person)
    }
}