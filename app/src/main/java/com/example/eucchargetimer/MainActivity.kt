package com.example.eucchargetimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ImageButton
import android.widget.EditText
import android.widget.Switch
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import android.widget.TextView


class MainActivity : AppCompatActivity(),OnSeekBarChangeListener {
    var seekBarNormal: SeekBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vmax = 84
        //var accum_value = findViewById(R.id.value_actual) as EditText
        //var x = accum_value.text.toString().toInt()

        seekBarNormal= findViewById(R.id.seekBar)
        seekBarNormal?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
                //Toast.makeText(applicationContext, "seekbar progress: $progress", Toast.LENGTH_SHORT).show()
                var percent_now = findViewById(R.id.percent) as EditText

                var z = progress.toInt()
                percent_now.setText("$z")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //Toast.makeText(applicationContext, "seekbar touch started!", Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //Toast.makeText(applicationContext, "seekbar touch stopped!", Toast.LENGTH_SHORT).show()
            }

        })


        var x2 = false
        var time_of_sharge1 = findViewById(R.id.time_of_sharge) as EditText
        var amper: Float = 1.5F
        //var multiplier = findViewById(R.id.switch1) as Switch


        var timer = findViewById(R.id.button) as ImageButton



        fun calculate() {

            var percent_of_charge = findViewById(R.id.percent) as EditText
            var y = percent_of_charge.text.toString().toInt()
            var sw1 = findViewById(R.id.switch1) as Switch
            sw1?.setOnCheckedChangeListener { _, isChecked ->
                //var amper = if (isChecked) 3.0f else 1.5f
                x2 = sw1.isChecked
            }
            if (x2 == true) {
                amper = 3.0f
            } else {
                amper = 1.5f
            }
            var ah = 1600/84
            var pah = ah - (y * ah / 100)    // рассчёт ампер-часов
            //var value_acc = x
            var time = pah / amper
            time = ((time * 10.0).toInt() / 10.0).toFloat()
            var hours = time.toInt()
            var minutes = ((time - hours)*60).toInt()
            var minutes_str = minutes.toString()
            var hours_str = hours.toString()
            var time1 = time.toString()

            var hours_set1 = findViewById(R.id.hours_set) as EditText
            var minutes_set1 = findViewById(R.id.minutes_set) as EditText

            time_of_sharge1.setText(time1)
            var start_h = findViewById(R.id.time_of_trip_h) as EditText
            var check_h = start_h.text.toString().toInt()
            while (check_h > 23) {
               check_h = check_h - 24
           }
            start_h.setText(check_h.toString())
            var start_m = findViewById(R.id.time_of_trip_m) as EditText
            var check_m = start_m.text.toString().toInt()
            while (check_m > 59) {
                check_m = check_m - 60
            }
            start_m.setText(check_m.toString())

            var start_m_int = check_m
            // считаем время включения в сеть
            var minutes_of_plugin = start_m_int - minutes
            var hours_of_plugin = start_h.text.toString().toInt() - hours
            if (minutes_of_plugin < 0) {
                hours_of_plugin = hours_of_plugin - 1
                minutes_of_plugin = 60 + minutes_of_plugin
            }

            if (hours_of_plugin <0) {
                hours_of_plugin = 24 + hours_of_plugin
            }

            val hours_of_plugin1 = hours_of_plugin.toString()
            var minutes_of_plugin1 = minutes_of_plugin.toString()


            hours_set1.setText(hours_of_plugin1)
            minutes_set1.setText(minutes_of_plugin1)
            }

        timer.setOnClickListener {
            calculate()
        }


    }
    override fun onProgressChanged(seekBar: SeekBar, persent: Int,
                                    fromUser: Boolean) {
        // called when progress is changed
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        // called when tracking the seekbar is started
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        // called when tracking the seekbar is stopped
    }
}