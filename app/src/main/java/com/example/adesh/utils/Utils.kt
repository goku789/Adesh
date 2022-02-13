package com.example.adesh.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import java.text.SimpleDateFormat
import java.util.*


object Utils {

    fun showDatePickerDialog(context: Context, editText: AppCompatEditText) {
        val myCalendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                val day: String = if (i3 < 10) {
                    "0$i3"
                } else {
                    "$i3"
                }

                val monthvalue=i2+1
                val month: String = if (monthvalue < 10) {
                    "0${monthvalue}"
                } else {
                    "$monthvalue"
                }
                val date= "$day-$month-$i"
                editText.setText(date)
            },
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentData(): String {
        return SimpleDateFormat("dd-MM-yyyy").format(Date())
    }

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}