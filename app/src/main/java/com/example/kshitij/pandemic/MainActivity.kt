package com.example.kshitij.pandemic

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kshitij.pandemic.adapters.PersonClassAdaper
import com.example.kshitij.pandemic.models.Person
import com.example.kshitij.pandemic.ui.home.PersonFragment
import com.example.kshitij.pandemic.ui.singlePerson.SinglePersonFragment

class MainActivity : AppCompatActivity(), PersonFragment.itemcClickListener {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_people, R.id.navigation_dashboard, R.id.navigation_add_person, R.id.singlePersonFragment, R.id.addHealthFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun onItemClicked(person: Person) {
        val bundle: Bundle = Bundle()
        bundle.putSerializable("Person", person)
        navController.navigate(R.id.singlePersonFragment, bundle)
    }


}
