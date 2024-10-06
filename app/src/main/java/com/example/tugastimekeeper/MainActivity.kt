package com.example.tugastimekeeper

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugastimekeeper.databinding.ActivityMainBinding
import com.google.android.material.resources.TextAppearance

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
result->
        if (result.resultCode == Activity.RESULT_OK){
            val data= result.data
            binding.homePageImage.setVisibility(View.GONE)

            with(binding){
                var ly = LinearLayout(this@MainActivity)
                ly.background = resources.getDrawable(R.drawable.time_card)
                ly.orientation = LinearLayout.VERTICAL
                ly.setPadding(24,30,24,30)

                var dateHolder = LinearLayout(this@MainActivity)
                dateHolder.orientation = LinearLayout.HORIZONTAL
                dateHolder.setGravity(Gravity.CENTER_VERTICAL)

                var dateDrawable = ImageView(this@MainActivity)
                dateDrawable.background = resources.getDrawable(R.drawable.outline_calendar_today_24)
                dateDrawable.layoutParams = LinearLayout.LayoutParams(
                    32, 32
                )

                dateHolder.addView(dateDrawable)
                var dateCard = TextView(this@MainActivity)
                dateCard.setText(data?.getStringExtra("tanggal"))
                dateHolder.addView(dateCard)

                dateCard.setPadding(10,0,0,0)
                dateHolder.setPadding(0,50,0,0)


                var title = TextView(this@MainActivity)
                title.setText( data?.getStringExtra("judul"))
                title.setTextSize(20.0F)
                title.setTypeface(Typeface.DEFAULT_BOLD)
                title.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                title.textAlignment = TextView.TEXT_ALIGNMENT_CENTER

                var timeHolder = LinearLayout(this@MainActivity)
                timeHolder.orientation = LinearLayout.HORIZONTAL
                timeHolder.setGravity(Gravity.CENTER_VERTICAL)

                var timeDrawable = ImageView(this@MainActivity)
                timeDrawable.background = resources.getDrawable(R.drawable.baseline_access_time_24)
                timeDrawable.layoutParams = LinearLayout.LayoutParams(
                    32, 32
                )

                timeHolder.addView(timeDrawable)
                var timeCard = TextView(this@MainActivity)
                timeCard.setText(data?.getStringExtra("waktu"))
                timeHolder.addView(timeCard)


                timeCard.setPadding(10,0,0,0)
                timeHolder.setPadding(0,0,0,0)

                var repeatHolder = LinearLayout(this@MainActivity)
                repeatHolder.orientation = LinearLayout.HORIZONTAL
                repeatHolder.setGravity(Gravity.CENTER_VERTICAL)

                var repeatDrawable = ImageView(this@MainActivity)
                repeatDrawable.background = resources.getDrawable(R.drawable.baseline_access_time_24)
                repeatDrawable.layoutParams = LinearLayout.LayoutParams(
                    32, 32
                )

                repeatHolder.addView(repeatDrawable)
                var repeatCard = TextView(this@MainActivity)
                repeatCard.setText(data?.getStringExtra("repeat"))
                repeatHolder.addView(repeatCard)


                repeatCard.setPadding(10,0,0,0)
                repeatHolder.setPadding(0,0,0,30)


                ly.addView(title)
                ly.addView(dateHolder)
                ly.addView(timeHolder)
                ly.addView(repeatHolder)
                timeValue.addView(ly)

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            addTaskButton.setOnClickListener {
                var intent = Intent(this@MainActivity, AddPage::class.java)
                launcher.launch(intent)
            }
        }
    }
}