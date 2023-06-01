package com.excitedbroltd.roomdatabaseandroid

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.excitedbroltd.roomdatabaseandroid.adapter.RecyclerViewAdapter
import com.excitedbroltd.roomdatabaseandroid.databinding.ActivityMainBinding
import com.excitedbroltd.roomdatabaseandroid.myviemodel.PersonViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var personViewModel: PersonViewModel
    private var mlist = emptyList<Person>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        personViewModel = ViewModelProvider(this)[PersonViewModel::class.java]
        binding.btnSubmit.setOnClickListener {
            val name = binding.personName.text
            val age = binding.personAge.text
            val country = binding.personCountry.text
            if (name.isNotEmpty() && age.isNotEmpty() && country.isNotEmpty()) {
                personViewModel.addPerson(
                    0,
                    name.toString(),
                    Integer.parseInt(age.toString()),
                    country.toString()
                )
                age.clear()
                name.clear()
                country.clear()
            } else {
                Toast.makeText(this, "Fill all the field", Toast.LENGTH_SHORT).show()
            }
        }

        personViewModel.getAllPerson().observe(this) {
            mlist = it
            val rv = RecyclerViewAdapter(mlist)
            binding.rvView.layoutManager = LinearLayoutManager(this)
            binding.rvView.adapter = rv
            Log.d("DEMU", "onCreate: ${mlist.size}")
        }

    }
}