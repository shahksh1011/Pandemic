package com.example.kshitij.pandemic.ui.singlePerson

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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kshitij.pandemic.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
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
        val oxygenChart: LineChart = root.findViewById(R.id.chart_oxygen_level)

        val xAxis = chart.xAxis
        xAxis.isEnabled = false

        val oxyxAxi = oxygenChart.xAxis
        oxyxAxi.isEnabled = false
//        xAxis.valueFormatter = object : ValueFormatter() {
//            override fun getFormattedValue(value: Float): String {
//                return months.get(value.toInt() % months.length)
//            }
//        }
        val valList= mutableListOf<Entry>()
        val oxygenValList = mutableListOf<Entry>()
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
//        val listOfHealthData = singlePersonViewModel.healthList
//        var x = 0f
//        listOfHealthData.observeForever {
//            it.forEach {
//                valList.add(Entry(x, it.bodyTemperature.toFloat()))
//                x=x+1
//                Log.d("HealthData", it.dateEntered)
//            }
//        }
//
        valList.add(Entry(1f, 35f))
        valList.add(Entry(2f, 37f))
        valList.add(Entry(3f, 36.5f))
        valList.add(Entry(4f, 36.8f))
        valList.add(Entry(5f, 36.8f))
        valList.add(Entry(6f, 38f))

        oxygenValList.add(Entry(1f, 75f))
        oxygenValList.add(Entry(2f, 87f))
        oxygenValList.add(Entry(3f, 86.5f))
        oxygenValList.add(Entry(4f, 96.8f))
        oxygenValList.add(Entry(5f, 76.8f))
        oxygenValList.add(Entry(6f, 80f))

        var set1: LineDataSet
        var set2: LineDataSet

        set1 = LineDataSet(valList, "Body Temperature")
        set1.setDrawIcons(false)

        set2 = LineDataSet(oxygenValList, "Oxygen Level")

        set1.enableDashedLine(10f, 5f, 0f)
        set2.enableDashedLine(10f, 5f, 0f)

        set1.enableDashedHighlightLine(10f, 5f, 0f)
        set2.enableDashedHighlightLine(10f, 5f, 0f)

        set1.setColor(Color.DKGRAY)
        set2.setColor(Color.DKGRAY)

        set1.setCircleColor(Color.DKGRAY)
        set1.lineWidth = 1f
        set1.circleRadius = 3f
        set1.setDrawCircleHole(true)
        set1.setValueTextSize(9f)

        set2.setCircleColor(Color.DKGRAY)
        set2.lineWidth = 1f
        set2.circleRadius = 3f
        set2.setDrawCircleHole(true)
        set2.setValueTextSize(9f)
//        set1.setDrawFilled(true)
        set1.setFormLineWidth(1f)
        set1.setFormLineDashEffect(DashPathEffect(floatArrayOf(10f, 5f), 0f))
        set1.formSize = 1.5f

        set2.setFormLineWidth(1f)
        set2.setFormLineDashEffect(DashPathEffect(floatArrayOf(10f, 5f), 0f))
        set2.formSize = 1.5f

        var dataSets = mutableListOf<ILineDataSet>()
        dataSets.add(set1)

        var oxygenDataSets = mutableListOf<ILineDataSet>()
        oxygenDataSets.add(set2)

        val data: LineData = LineData(dataSets)
        chart.data = data

        val oxyData: LineData = LineData(oxygenDataSets)
        oxygenChart.data = oxyData






//        val data: BarData = BarData(getXAxisValues(), getDataSet())
        return root
    }


}
