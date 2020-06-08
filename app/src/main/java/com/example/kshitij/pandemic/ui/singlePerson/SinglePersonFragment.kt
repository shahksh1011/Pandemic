package com.example.kshitij.pandemic.ui.singlePerson

import android.app.Person
import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kshitij.pandemic.R
import com.example.kshitij.pandemic.models.PersonHealthData
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.io.Serializable


class SinglePersonFragment : Fragment() {

    private lateinit var singlePersonViewModel: SinglePersonViewModel
    companion object {
        fun newInstance() = SinglePersonFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//val currBundle : Bundle = arguments?.getBundle("Person")!!
        val bundle: Serializable? = arguments?.getSerializable("Person")
        val person: com.example.kshitij.pandemic.models.Person = bundle as com.example.kshitij.pandemic.models.Person
        Log.d("Here", person.id.toString())
        val root = inflater.inflate(R.layout.single_person_fragment, container, false)
        val chart: LineChart = root.findViewById(R.id.chart)
        val valList= mutableListOf<Entry>()
        val personName = root.findViewById<TextView>(R.id.person_name)
        personName.text = person.firstName + " " + person.lastName

        val imageButton = root.findViewById<ImageButton>(R.id.button_add_health)
        imageButton.setOnClickListener {
            val sendArguments = Bundle()
            sendArguments.putSerializable("Person", person)
            findNavController().navigate(R.id.addHealthFragment, sendArguments)
        }
        singlePersonViewModel = ViewModelProvider(this).get(SinglePersonViewModel::class.java)
        singlePersonViewModel.getSingleHealthData(personId = person.id)
        val listOfHealthData = singlePersonViewModel.healthList
        var x = 0f
        listOfHealthData.observeForever {
            it.forEach {
                valList.add(Entry(x, it.bodyTemperature.toFloat()))
                x=x+1
                Log.d("HealthData", it.dateEntered)
            }
        }
//
//        valList.add(Entry("", 50f))
//        valList.add(Entry(2f, 70f))
//        valList.add(Entry(3f, 65f))
        var set1: LineDataSet
        set1 = LineDataSet(valList, "Sample Data")
        set1.setDrawIcons(false)
        set1.enableDashedLine(10f, 5f, 0f)
        set1.enableDashedHighlightLine(10f, 5f, 0f)
        set1.setColor(Color.DKGRAY)
        set1.setCircleColor(Color.DKGRAY)
        set1.lineWidth = 1f
        set1.circleRadius = 3f
        set1.setDrawCircleHole(true)
        set1.setValueTextSize(9f)
//        set1.setDrawFilled(true)
        set1.setFormLineWidth(1f)
        set1.setFormLineDashEffect(DashPathEffect(floatArrayOf(10f, 5f), 0f))
        set1.formSize = 1.5f
        var dataSets = mutableListOf<ILineDataSet>()
        dataSets.add(set1)
        val data: LineData = LineData(dataSets)
        chart.data = data

//        val data: BarData = BarData(getXAxisValues(), getDataSet())
        return root
    }


}
