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

package org.theta4j.plugin;

public final class ThetaIntent {
    private ThetaIntent() {
        throw new AssertionError("unreachable");
    }

    // Receiving Button Events

    public static final String ACTION_KEY_DOWN = "com.theta360.plugin.ACTION_KEY_DOWN";

    public static final String ACTION_KEY_UP = "com.theta360.plugin.ACTION_KEY_UP";

    public static final String EXTRA_KEY_CODE = "keyCode";

    public static final String EXTRA_KEY_EVENT = "KeyEvent";

    public static final int KEY_CODE_SHUTTER = 27;

    public static final int KEY_CODE_MODE = 130;

    public static final int KEY_CODE_WIRELESS = 284;

    // Controlling LEDs

    public static final String ACTION_LED_SHOW = "com.theta360.plugin.ACTION_LED_SHOW";

    public static final String ACTION_LED_BLINK = "com.theta360.plugin.ACTION_LED_BLINK";

    public static final String ACTION_LED_HIDE = "com.theta360.plugin.ACTION_LED_HIDE";

    public static final String EXTRA_LED_TARGET = "target";

    public static final String LED_TARGET_3 = "LED3";

    public static final String LED_TARGET_4 = "LED4";

    public static final String LED_TARGET_5 = "LED5";

    public static final String LED_TARGET_6 = "LED6";

    public static final String LED_TARGET_7 = "LED7";

    public static final String LED_TARGET_8 = "LED8";

    public static final String EXTRA_LED_COLOR = "color";

    public static final String LED_COLOR_BLUE = "blue";

    public static final String LED_COLOR_GREEN = "green";

    public static final String LED_COLOR_RED = "red";

    public static final String LED_COLOR_CYAN = "cyan";

    public static final String LED_COLOR_MAGENTA = "magenta";

    public static final String LED_COLOR_YELLOW = "yellow";

    public static final String LED_COLOR_WHITE = "white";

    public static final String EXTRA_LED_PERIOD = "period";

    // Controlling Speakers

    public static final String ACTION_AUDIO_SHUTTER = "com.theta360.plugin.ACTION_AUDIO_SHUTTER";

    public static final String ACTION_AUDIO_SH_OPEN = "com.theta360.plugin.ACTION_AUDIO_SH_OPEN";

    public static final String ACTION_AUDIO_SH_CLOSE = "com.theta360.plugin.ACTION_AUDIO_SH_CLOSE";

    public static final String ACTION_AUDIO_MOVSTART = "com.theta360.plugin.ACTION_AUDIO_MOVSTART";

    public static final String ACTION_AUDIO_MOVSTOP = "com.theta360.plugin.ACTION_AUDIO_MOVSTOP";

    public static final String ACTION_AUDIO_SELF = "com.theta360.plugin.ACTION_AUDIO_SELF";

    public static final String ACTION_AUDIO_WARNING = "com.theta360.plugin.ACTION_AUDIO_WARNING";

    // Controlling Wi-Fi

    public static final String ACTION_WLAN_OFF = "com.theta360.plugin.ACTION_WLAN_OFF";

    public static final String ACTION_WLAN_AP = "com.theta360.plugin.ACTION_WLAN_AP";

    public static final String ACTION_WLAN_CL = "com.theta360.plugin.ACTION_WLAN_CL";

    // Updating the Database

    public static final String ACTION_DATABASE_UPDATE = "com.theta360.plugin.ACTION_DATABASE_UPDATE";

    public static final String EXTRA_DATABASE_TARGETS = "targets";

    // Notifying Errors

    public static final String ACTION_ERROR_OCCURED = "com.theta360.plugin.ACTION_ERROR_OCCURED";

    // Notifying Completion of Plug-in

    public static final String ACTION_PLUGIN_FINISH = "com.theta360.plugin.ACTION_FINISH_PLUGIN";

    public static final String EXTRA_PLUGIN_PACKAGE_NAME = "packageName";

    public static final String EXTRA_PLUGIN_EXIT_STATUS = "exitStatus";

    public static final String PLUGIN_EXIT_STATUS_SUCCESS = "success";

    public static final String PLUGIN_EXIT_STATUS_FAILURE = "failure";

    public static final String EXTRA_PLUGIN_MESSAGE = "message";

    // Notifying Camera Device Control

    public static final String ACTION_MAIN_CAMERA_CLOSE = "com.theta360.plugin.ACTION_MAIN_CAMERA_CLOSE";

    public static final String ACTION_MAIN_CAMERA_OPEN = "com.theta360.plugin.ACTION_MAIN_CAMERA_OPEN";
}
