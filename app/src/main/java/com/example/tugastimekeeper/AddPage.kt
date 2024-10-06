package com.example.tugastimekeeper

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.tugastimekeeper.databinding.ActivityAddPageBinding
import com.example.tugastimekeeper.dialog.DatePickerBuatan
import com.example.tugastimekeeper.dialog.TimePickerBuatan
import java.util.Locale

class AddPage : AppCompatActivity(), OnDateSetListener, OnTimeSetListener {
    lateinit var binding: ActivityAddPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repeatArray = resources.getStringArray(R.array.repeat_array)
        with(binding){
            val adapterRepeat = ArrayAdapter(this@AddPage, android.R.layout.simple_spinner_dropdown_item, repeatArray)
            repeatSpiner.adapter = adapterRepeat
        }
    }

    override fun onTimeSet(view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) {
        with(binding){
            timeValue.setText(String.format(
                Locale.getDefault(),
                "%02d:%02d", hourOfDay,minute))
        }
    }

    override fun onDateSet(
        view: android.widget.DatePicker?,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        with(binding){
            dateValue.setText(String.format(
                Locale.getDefault(),
                "%02d/%02d/%04d", dayOfMonth,month,year))
        }
    }


    fun showTime(view: View) {
        TimePickerBuatan().show(supportFragmentManager, "timePicker")
    }
    fun showDate(view: View) {
        DatePickerBuatan().show(supportFragmentManager, "Datepicker")
    }

    fun showAlert(view: View){
        val builder = AlertDialog.Builder(this@AddPage)
        builder.setTitle("SimpleRemind")
        builder.setMessage("Do yo want to add this as new task?")
        builder.setPositiveButton("Yes"){ _, _ ->
            with(binding){

                    var intent = Intent()

                    intent.putExtra("judul", inputJudul.text.toString())
                    intent.putExtra("tanggal", dateValue.text.toString())
                    intent.putExtra("waktu", timeValue.text.toString())
                    intent.putExtra("repeat", repeatSpiner.selectedItem.toString())


                    setResult(Activity.RESULT_OK, intent)

                    finish()
            }
        }
        builder.setNegativeButton("No"){ dialog, _ ->
            dialog.dismiss()
        }
        val  alertDialog = builder.create()
        alertDialog.show()
    }
}
