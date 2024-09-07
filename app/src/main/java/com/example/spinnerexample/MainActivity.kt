package com.example.spinnerexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Reference al AutoCompleteTextView
        val autoCompleteTextView: AutoCompleteTextView = findViewById(R.id.autoCompleteTextView)

        // Adjust the size del ícon (drawable)
        val drawable = ContextCompat.getDrawable(this, R.drawable.ic_globe)
        drawable?.let {
            // Adjust the size to drawable
            val wrappedDrawable = DrawableCompat.wrap(it)
            val iconSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, resources.displayMetrics).toInt()
            wrappedDrawable.setBounds(0, 0, iconSizePx, iconSizePx)
            autoCompleteTextView.setCompoundDrawablesRelative(wrappedDrawable, null, null, null)
        }

        // Define the space between to ícon and text (in píxels)
        val spaceBetweenIconAndTextPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics).toInt()
        autoCompleteTextView.compoundDrawablePadding = spaceBetweenIconAndTextPx

        // Options for  AutoCompleteTextView (dropdown)
        val languages = arrayOf("English", "Spanish", "German")

        // Create a ArrayAdapter using the layout default of Android and the list de languages
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, languages)

        // Apply the adapter to AutoCompleteTextView
        autoCompleteTextView.setAdapter(adapter)

        // Comportment initial
        autoCompleteTextView.setText("English", false)

        // Manage selection to item and show in a Toast
        autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }
}

