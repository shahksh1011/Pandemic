package com.example.kshitij.pandemic.ui.singlePerson

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kshitij.pandemic.PersonHealthDao
import com.example.kshitij.pandemic.db.PersonDatabase
import com.example.kshitij.pandemic.models.PersonHealthData
import com.example.kshitij.pandemic.repositories.PersonHealthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SinglePersonViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var repository: PersonHealthRepository
    lateinit var healthList: LiveData<List<PersonHealthData>>
    private val personHealthDao: PersonHealthDao
    init {
        personHealthDao = PersonDatabase.getDatabase(application, viewModelScope).PersonHealthDao()
        repository = PersonHealthRepository(personHealthDao, 1)
        healthList = repository.allHeathData
    }

    fun getSingleHealthData(personId: Int){
        repository = PersonHealthRepository(personHealthDao = personHealthDao, personId =personId )
        healthList = repository.allHeathData
    }

    fun insert(personHealthData: PersonHealthData) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(personHealthData)
    }

}
