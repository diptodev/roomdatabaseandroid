package com.excitedbroltd.roomdatabaseandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.excitedbroltd.roomdatabaseandroid.adapter.RecyclerViewAdapter
import com.excitedbroltd.roomdatabaseandroid.databinding.ActivityMainBinding
import com.excitedbroltd.roomdatabaseandroid.myviemodel.PersonViewModel

class MainActivity : AppCompatActivity(), RecyclerListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var personViewModel: PersonViewModel
    private var mlist = emptyList<Person>()
    private lateinit var dialog: AlertDialog.Builder
    private var id = 0

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        personViewModel = ViewModelProvider(this)[PersonViewModel::class.java]
        dialog = AlertDialog.Builder(this)
        binding.btnSubmit.setOnClickListener {
            val name = binding.personName.text
            val age = binding.personAge.text
            val country = binding.personCountry.text
            if (name.isNotEmpty() && age.isNotEmpty() && country.isNotEmpty()) {
                if (binding.btnSubmit.text.toString() == "Update") {
                    personViewModel.updatePerson(
                        id, name.toString(),
                        Integer.parseInt(age.toString()),
                        country.toString()
                    )
                    binding.btnSubmit.text = "Submit"
                } else {
                    personViewModel.addPerson(
                        0,
                        name.toString(),
                        Integer.parseInt(age.toString()),
                        country.toString()
                    )
                }
                age.clear()
                name.clear()
                country.clear()
            } else {
                Toast.makeText(this, "Fill all the field", Toast.LENGTH_SHORT).show()
            }
        }
        val rv = RecyclerViewAdapter(this)
        binding.rvView.layoutManager = LinearLayoutManager(this)
        binding.rvView.adapter = rv
        personViewModel.getAllPerson().observe(this) {
            rv.setData(it)
            rv.notifyDataSetChanged()
        }

    }

    override fun onClick(person: Person, positon: Int) {
        Toast.makeText(this, "$positon", Toast.LENGTH_SHORT).show()
        dialog.setTitle("Update or delete data")
            .setPositiveButton("Update") { dialog, _ ->
                updatePerson(person)
                dialog.dismiss()
            }
            .setNegativeButton("Delete") { dialog, _ ->
                deletePerson(person)
                dialog.dismiss()
            }
            .show()
            .setCancelable(true)
    }

    private fun deletePerson(person: Person) {
        personViewModel.deletePerson(person)
        Toast.makeText(this, "Person Deleted", Toast.LENGTH_SHORT).show()
    }

    private fun updatePerson(person: Person) {
        binding.btnSubmit.text = "Update"
        binding.personAge.setText(person.age.toString())
        binding.personCountry.setText(person.country)
        binding.personName.setText(person.name)
        id = person.id
        Toast.makeText(this, "Person updated", Toast.LENGTH_SHORT).show()

    }
}