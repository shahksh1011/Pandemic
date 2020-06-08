package com.example.kshitij.pandemic.ui.covidcases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kshitij.pandemic.R
import com.example.kshitij.pandemic.adapters.CovidCasesAdapter
import com.example.kshitij.pandemic.models.response.CountryDataItem
import org.koin.android.viewmodel.ext.android.viewModel


class CovidCasesFragment : Fragment() {

    private val covidCasesViewModel: CovidCasesViewModel by viewModel()
    private lateinit var covidCasesAdapter: CovidCasesAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        covidCasesViewModel = ViewModelProvider(this).get(CovidCasesViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_fragment_covid_cases)
        recyclerView.layoutManager = LinearLayoutManager(context)

        covidCasesViewModel.getAllData()
        covidCasesViewModel.listOfUpdates.observe(viewLifecycleOwner, Observer(function = fun(updateList: CountryDataItem?){
            updateList?.let {
                covidCasesAdapter = context?.let { it1 -> CovidCasesAdapter(it1, updateList.countries) }!!
                recyclerView.adapter = covidCasesAdapter
            }
        }))
        return root
    }
}
