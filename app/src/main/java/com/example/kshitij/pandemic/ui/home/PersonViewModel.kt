package com.example.kshitij.pandemic.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.kshitij.pandemic.db.PersonDatabase
import com.example.kshitij.pandemic.models.Person
import com.example.kshitij.pandemic.repositories.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PersonViewModel(application: Application): AndroidViewModel(application){
    private val repository: PersonRepository
    val listOfPeople : LiveData<List<Person>>
    init {
        val personDao= PersonDatabase.getDatabase(application, viewModelScope).PersonDao()
        repository = PersonRepository(personDao)
        listOfPeople = repository.allPeople
    }
    fun insert(person: Person) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(person)
    }
}