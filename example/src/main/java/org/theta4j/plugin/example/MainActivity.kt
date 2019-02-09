/*
 * Copyright (C) 2018 theta4j project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theta4j.plugin.example

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.theta4j.plugin.*

class MainActivity : ThetaPluginActivity() {
    companion object {
        private val TAG = "THETA_PLUGIN_SDK"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Controlling LED
        spinner_led_target.adapter = ArrayAdapter<LEDTarget>(applicationContext, android.R.layout.simple_list_item_1, LEDTarget.values())
        spinner_led_color.adapter = object : ArrayAdapter<LEDColor?>(applicationContext, android.R.layout.simple_list_item_1) {
            init {
                add(null) // default value for LED3
                addAll(LEDColor.values().toList())
            }
        }
        spinner_led_period.adapter = ArrayAdapter<Int>(applicationContext, android.R.layout.simple_list_item_1, arrayOf(null, 300, 2000))
        button_show_led.setOnClickListener {
            val target = spinner_led_target.selectedItem as LEDTarget
            val color = spinner_led_color.selectedItem as LEDColor?
            if (color != null) {
                showLED(target, color)
            } else {
                showLED(target)
            }
        }
        button_blink_led.setOnClickListener {
            val target = spinner_led_target.selectedItem as LEDTarget
            val color = spinner_led_color.selectedItem as LEDColor?
            val period = spinner_led_period.selectedItem as Int?
            if (color != null && period != null) {
                blinkLED(target, color, period)
            } else if (color != null && period == null) {
                blinkLED(target, color)
            } else if (color == null && period != null) {
                blinkLED(target, period)
            } else {
                blinkLED(target)
            }
        }
        button_hide_led.setOnClickListener {
            val target = spinner_led_target.selectedItem as LEDTarget
            hideLED(target)
        }

        // Controlling Speaker
        spinner_preset_sound.adapter = ArrayAdapter<PresetSound>(applicationContext, android.R.layout.simple_list_item_1, PresetSound.values())
        button_ring.setOnClickListener {
            ring(spinner_preset_sound.selectedItem as PresetSound)
        }

        // Controlling WLAN mode
        spinner_wlan_mode.adapter = ArrayAdapter<WLanMode>(applicationContext, android.R.layout.simple_list_item_1, WLanMode.values())
        spinner_wlan_mode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // ignore
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                setWLanMode(spinner_wlan_mode.selectedItem as WLanMode)
            }
        }

        // Other
        button_throw_runtime_exception.setOnClickListener {
            throw RuntimeException("RuntimeException for test")
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        Log.d(TAG, "dispatchKeyEvent($event)")
        return super.dispatchKeyEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d(TAG, "onKeyDown($keyCode, $event)")
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d(TAG, "onKeyUp($keyCode, $event)")
        return super.onKeyUp(keyCode, event)
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d(TAG, "onKeyLongPress($keyCode, $event)")
        return super.onKeyLongPress(keyCode, event)
    }
}
