package com.example.educonnect.ui.auth.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.educonnect.R
import com.example.educonnect.databinding.FragmentSignupStep1Binding
import com.example.educonnect.ui.auth.StudentSignUpActivity
import java.text.SimpleDateFormat
import java.util.*

class SignUpStep1Fragment : Fragment() {
    
    private var _binding: FragmentSignupStep1Binding? = null
    private val binding get() = _binding!!
    
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private var selectedDate: Calendar? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupStep1Binding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupClassDropdown()
        setupDatePicker()
    }
    
    private fun setupClassDropdown() {
        val classes = arrayOf(
            "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5",
            "Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10",
            "Grade 11", "Grade 12", "Undergraduate", "Graduate"
        )
        
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, classes)
        binding.etClass.setAdapter(adapter)
    }
    
    private fun setupDatePicker() {
        binding.etDateOfBirth.setOnClickListener {
            showDatePicker()
        }
    }
    
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        
        // Set max age to 5 years old (for school students)
        calendar.add(Calendar.YEAR, -5)
        val maxDate = calendar.timeInMillis
        
        // Set min age to 30 years old
        calendar.add(Calendar.YEAR, -25) // total -30 years
        val minDate = calendar.timeInMillis
        
        // Set initial date to 10 years ago
        calendar.add(Calendar.YEAR, 15) // total -15 years
        
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                val selected = Calendar.getInstance()
                selected.set(year, month, dayOfMonth)
                selectedDate = selected
                binding.etDateOfBirth.setText(dateFormatter.format(selected.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        
        // Set date limits
        datePickerDialog.datePicker.maxDate = maxDate
        datePickerDialog.datePicker.minDate = minDate
        
        datePickerDialog.show()
    }
    
    fun validateAndSaveData(): Boolean {
        var isValid = true
        
        // Validate first name
        val firstName = binding.etFirstName.text.toString().trim()
        if (firstName.isEmpty()) {
            binding.tilFirstName.error = "First name is required"
            isValid = false
        } else if (firstName.length < 2) {
            binding.tilFirstName.error = "First name must be at least 2 characters"
            isValid = false
        } else {
            binding.tilFirstName.error = null
        }
        
        // Validate last name
        val lastName = binding.etLastName.text.toString().trim()
        if (lastName.isEmpty()) {
            binding.tilLastName.error = "Last name is required"
            isValid = false
        } else if (lastName.length < 2) {
            binding.tilLastName.error = "Last name must be at least 2 characters"
            isValid = false
        } else {
            binding.tilLastName.error = null
        }
        
        // Validate class
        val selectedClass = binding.etClass.text.toString().trim()
        if (selectedClass.isEmpty()) {
            binding.tilClass.error = "Please select a class/grade"
            isValid = false
        } else {
            binding.tilClass.error = null
        }
        
        // Validate date of birth
        val dateOfBirth = binding.etDateOfBirth.text.toString().trim()
        if (dateOfBirth.isEmpty()) {
            binding.tilDateOfBirth.error = "Date of birth is required"
            isValid = false
        } else {
            binding.tilDateOfBirth.error = null
        }
        
        // Save data if valid
        if (isValid) {
            val signUpActivity = activity as StudentSignUpActivity
            signUpActivity.studentData.apply {
                put("firstName", firstName)
                put("lastName", lastName)
                put("class", selectedClass)
                put("dateOfBirth", dateOfBirth)
                put("fullName", "$firstName $lastName")
            }
        }
        
        return isValid
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 