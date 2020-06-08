package com.example.kshitij.pandemic.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.kshitij.pandemic.PersonHealthDao
import com.example.kshitij.pandemic.models.PersonHealthData

class PersonHealthRepository(private val personHealthDao: PersonHealthDao, private val personId: Int) {
    val allHeathData: LiveData<List<PersonHealthData>> = personHealthDao.getAllHealthData(personId)
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(personHealthData: PersonHealthData){
        personHealthDao.insert(personHealthData)
    }
}