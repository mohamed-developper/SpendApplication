package tn.org.spendapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class AddTranscationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transcation)

        // Spinner / autocomplete
        val typeList = findViewById<AutoCompleteTextView>(R.id.type_list)
        val list = listOf("Type One", "Type Two", "Type Three")
        val adapter = ArrayAdapter(
            this,
            R.layout.type_item,
            R.id.type_title,
            list
        )
        typeList.setAdapter(adapter)
        // END spinner

        // Form Validation
        val validate = findViewById<Button>(R.id.validate)

        validate.setOnClickListener {
            val titleInput = findViewById<EditText>(R.id.title_input)
            val emailInput = findViewById<EditText>(R.id.email_input)
            val title = titleInput.textString()
            val email = emailInput.textString()
            if (title.isBlank()) {
                titleInput.error = "Empty Field"
                return@setOnClickListener
            }

            // Email Validation
            if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailInput.error = "Email not Valide"
                return@setOnClickListener
            }
            //END Email validation

            Snackbar.make(validate, "You are awesome", Snackbar.LENGTH_SHORT)
                .show()
        }
        // END Form Validation

        // Real time testing
        val amountInput = findViewById<EditText>(R.id.amount_input)
        amountInput.addTextChangedListener {
            Log.e("TAG", "onCreate: ${it.toString()}")
            if (it.toString().toInt() > 1000) {
                amountInput.error = "You are broke bro"
            }
        }
        //END Real time testing

        // Time Picker
        val timePickerButton = findViewById<Button>(R.id.time_pick)
        timePickerButton.setOnClickListener {
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(10)
                    .setTitleText("My Picker")
                    .build()

            picker.addOnPositiveButtonClickListener {
                timePickerButton.text = "${picker.hour} : ${picker.minute}"
            }

            picker.show(supportFragmentManager, "TIME_PICKER")
        }
        // END time Picker

        // Date Picker
        val datePickerButton = findViewById<Button>(R.id.date_pick)
        datePickerButton.setOnClickListener {
            val picker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Pick Your date")
                    .build()

            picker.addOnPositiveButtonClickListener {
                datePickerButton.text = picker.headerText
            }

            picker.show(supportFragmentManager, "DATE_PICKER")
        }
        // END Date Picker


    }
}