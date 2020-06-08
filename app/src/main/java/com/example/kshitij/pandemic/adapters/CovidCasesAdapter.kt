package com.example.kshitij.pandemic.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kshitij.pandemic.R
import com.example.kshitij.pandemic.models.response.Country
import com.example.kshitij.pandemic.models.response.CountryDataItem

class CovidCasesAdapter internal constructor(
    context: Context,
    private val caseList: List<Country>
): RecyclerView.Adapter<CovidCasesAdapter.CovidCasesViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    inner class CovidCasesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val countryName = itemView.findViewById<TextView>(R.id.country_name_text)
        val activeCases = itemView.findViewById<TextView>(R.id.text_active_number)
        val totalCases = itemView.findViewById<TextView>(R.id.text_total_number)
        val recoveredCases = itemView.findViewById<TextView>(R.id.text_recovered_number)
        val totalDeaths = itemView.findViewById<TextView>(R.id.text_deaths_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidCasesViewHolder {

        val itemView = inflater.inflate(R.layout.recycler_view_single_country_item, parent, false)
        return CovidCasesViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return caseList.size
    }

    override fun onBindViewHolder(holder: CovidCasesViewHolder, position: Int) {
        val current = caseList[position]
        holder.countryName.text = current.country
        holder.activeCases.text = current.newConfirmed.toString()
        holder.recoveredCases.text = current.totalRecovered.toString()
        holder.totalCases.text = current.totalConfirmed.toString()
        holder.totalDeaths.text = current.totalDeaths.toString()
    }
}