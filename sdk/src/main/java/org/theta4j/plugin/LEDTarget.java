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

public enum LEDTarget {
    LED3(ThetaIntent.LED_TARGET_3),
    LED4(ThetaIntent.LED_TARGET_4),
    LED5(ThetaIntent.LED_TARGET_5),
    LED6(ThetaIntent.LED_TARGET_6),
    LED7(ThetaIntent.LED_TARGET_7),
    LED8(ThetaIntent.LED_TARGET_8);

    private final String value;

    LEDTarget(String value) {
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
