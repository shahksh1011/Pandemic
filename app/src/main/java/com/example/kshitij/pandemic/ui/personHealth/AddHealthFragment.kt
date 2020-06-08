package com.example.kshitij.pandemic.ui.personHealth

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider

import com.example.kshitij.pandemic.R
import com.example.kshitij.pandemic.models.PersonHealthData
import com.example.kshitij.pandemic.ui.singlePerson.SinglePersonViewModel
import java.io.Serializable
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddHealthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddHealthFragment : Fragment() {

    private lateinit var singlePersonViewModel: SinglePersonViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bundle: Serializable? = arguments?.getSerializable("Person")
        val person: com.example.kshitij.pandemic.models.Person = bundle as com.example.kshitij.pandemic.models.Person
        val root =inflater.inflate(R.layout.fragment_add_health, container, false)
        val calendar = Calendar.getInstance()
        val dateEdit = root.findViewById<EditText>(R.id.editText_health_date)
        val tempEdit = root.findViewById<EditText>(R.id.editText_body_temp)
        val oxxygenEdit = root.findViewById<EditText>(R.id.editText_oxygen_level)
        val addButton = root.findViewById<Button>(R.id.button_insert_health)
        addButton.setOnClickListener {
            if(TextUtils.isEmpty(dateEdit.text) || TextUtils.isEmpty(tempEdit.text) || TextUtils.isEmpty(oxxygenEdit.text)){

            }else{
                val personHealthData = PersonHealthData(tempEdit.text.toString(), oxxygenEdit.text.toString(),person.id, dateEdit.text.toString())
                singlePersonViewModel.insert(personHealthData)
            }
        }
        singlePersonViewModel = ViewModelProvider(this).get(SinglePersonViewModel::class.java)

        return root
    }


}
