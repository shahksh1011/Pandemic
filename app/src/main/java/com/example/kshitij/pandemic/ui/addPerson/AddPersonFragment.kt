package com.example.kshitij.pandemic.ui.addPerson

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kshitij.pandemic.R
import com.example.kshitij.pandemic.models.Person
import com.example.kshitij.pandemic.ui.home.PersonViewModel

class AddPersonFragment : Fragment() {

    private lateinit var personViewModel: PersonViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        notificationsViewModel =
//                ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        personViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_person, container, false)
        val firstNameText = root.findViewById<EditText>(R.id.edit_text_first_name)
        val lastNameText = root.findViewById<EditText>(R.id.edit_text_last_name)
        val age = root.findViewById<EditText>(R.id.edit_text_age)
        val buttonAddPerson = root.findViewById<Button>(R.id.button_add_person)

        buttonAddPerson.setOnClickListener {
            if(TextUtils.isEmpty(firstNameText.text) || TextUtils.isEmpty(lastNameText.text) || TextUtils.isEmpty(age.text)){
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
            }else{
                val p:Person = Person(firstNameText.text.toString(), lastName = lastNameText.text.toString(), age = age.text.toString())
                personViewModel.insert(p)

            }
        }
        return root
    }
}
