package com.example.kshitij.pandemic.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kshitij.pandemic.R
import com.example.kshitij.pandemic.adapters.PersonClassAdaper
import com.example.kshitij.pandemic.models.Person
import com.example.kshitij.pandemic.ui.singlePerson.SinglePersonFragment

class PersonFragment : Fragment(){



    private val newPersonActivityCode = 1
    private lateinit var personClassAdaper: PersonClassAdaper
    private lateinit var personViewModel: PersonViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_person, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.main_activity_recycler_View)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val listener = context as itemcClickListener

        personClassAdaper = context?.let { PersonClassAdaper(context = it,
            itemClickListener =listener
        ) }!!

        recyclerView.adapter = personClassAdaper

        personViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
        personViewModel.listOfPeople.observe(viewLifecycleOwner, Observer { person->
            person?.let {
                personClassAdaper.setPeople(it)
            }

        })
        return root
    }

    interface itemcClickListener {
        fun onItemClicked(person: Person)
    }
//    override fun onItemClicked(person: Person) {
//        TODO("Not yet implemented")
////        val nv = NavHostFragment.create(R.navigation.mobile_navigation)
////        nv.transa
////
//
//
//    }


}
