package com.example.memoryroadapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.memoryroadapp.R
import com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel
import com.example.memoryroadapp.databinding.ActivityAddEditLocationBinding

class AddEditLocationActivity : AppCompatActivity() {
    private val addEditLocationViewModel by lazy { ViewModelProvider(this).get(AddEditLocationViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_location)

        val binding = DataBindingUtil
            .setContentView<ActivityAddEditLocationBinding>(this, R.layout.activity_add_edit_location)
        binding.lifecycleOwner = this
        binding.viewmodel = addEditLocationViewModel
    }
}