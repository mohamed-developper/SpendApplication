package tn.org.spendapplication

import android.widget.EditText



// Kotlin Extensions
fun EditText.textString(): String {
    return this.text.toString()
}


fun sum(a: Int, b: Int): Int {
    return a + b
}