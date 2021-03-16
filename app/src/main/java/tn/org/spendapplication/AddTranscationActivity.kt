package tn.org.spendapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

class AddTranscationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transcation)

        // Spinner / autocomplete
        val typeList = findViewById<AutoCompleteTextView>(R.id.type_list)
        val list = listOf("Type One", "Type Two", "Type Three")
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            list
        )
        typeList.setAdapter(adapter)
        // END spinner

    }
}