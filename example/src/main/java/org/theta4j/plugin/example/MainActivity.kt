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

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.theta4j.plugin.*

class MainActivity : ThetaPluginActivity() {
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
        button_show_led.setOnClickListener {
            val target = spinner_led_target.selectedItem as LEDTarget
            val color = spinner_led_color.selectedItem as LEDColor?
            if (target == LEDTarget.LED3 && color != null) {
                showLED(target, color)
            } else {
                showLED(target)
            }
        }
        button_blink_led.setOnClickListener {
            val target = spinner_led_target.selectedItem as LEDTarget
            val color = spinner_led_color.selectedItem as LEDColor?
            if (target == LEDTarget.LED3 && color != null) {
                blinkLED(target, color)
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
                //throw AssertionError("spinner can not select nothing")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                setWLanMode(spinner_wlan_mode.selectedItem as WLanMode)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        val codeLabel = keyCodeToLabel(event!!.keyCode)
        val actionLabel = keyActionToLabel(event.action)
        text_dispatch_key_event.text = "$codeLabel $actionLabel"
        return super.dispatchKeyEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        text_on_key_down_event.text = keyCodeToLabel(keyCode)
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        text_on_key_up_event.text = keyCodeToLabel(keyCode)
        return super.onKeyUp(keyCode, event)
    }

    private fun keyActionToLabel(action: Int): String {
        return when (action) {
            KeyEvent.ACTION_DOWN -> "Down"
            KeyEvent.ACTION_UP -> "UP"
            else -> "$action"
        }
    }

    private fun keyCodeToLabel(code: Int): String {
        return when (code) {
            ThetaIntent.KEY_CODE_SHUTTER -> "Shutter"
            ThetaIntent.KEY_CODE_MODE -> "Mode"
            ThetaIntent.KEY_CODE_WIRELESS -> "Wireless"
            else -> "$code"
        }
    }
}
