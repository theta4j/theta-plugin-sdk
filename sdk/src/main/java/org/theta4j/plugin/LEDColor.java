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

public enum LEDColor {
    BLUE(ThetaIntent.LED_COLOR_BLUE),
    GREEN(ThetaIntent.LED_COLOR_GREEN),
    RED(ThetaIntent.LED_COLOR_RED),
    CYAN(ThetaIntent.LED_COLOR_CYAN),
    MAGENTA(ThetaIntent.LED_COLOR_MAGENTA),
    YELLOW(ThetaIntent.LED_COLOR_YELLOW),
    WHITE(ThetaIntent.LED_COLOR_WHITE);

    private final String value;

    LEDColor(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return value;
    }
}
