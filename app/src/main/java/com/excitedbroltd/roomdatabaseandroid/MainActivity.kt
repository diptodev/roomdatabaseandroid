package com.excitedbroltd.roomdatabaseandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.excitedbroltd.roomdatabaseandroid.databinding.ActivityMainBinding
import com.excitedbroltd.roomdatabaseandroid.myviemodel.PersonViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var personViewModel: PersonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        personViewModel = ViewModelProvider(this)[PersonViewModel::class.java]
        binding.room = personViewModel
    }
}