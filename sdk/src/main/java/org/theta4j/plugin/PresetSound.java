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

public enum PresetSound {
    SHUTTER("com.theta360.plugin.ACTION_AUDIO_SHUTTER"),
    SHUTTER_OPEN("com.theta360.plugin.ACTION_AUDIO_SH_OPEN"),
    SHUTTER_CLOSE("com.theta360.plugin.ACTION_AUDIO_SH_CLOSE"),
    MOVIE_START("com.theta360.plugin.ACTION_AUDIO_MOVSTART"),
    MOVIE_STOP("com.theta360.plugin.ACTION_AUDIO_MOVSTOP"),
    SELF("com.theta360.plugin.ACTION_AUDIO_SELF"),
    WARNING("com.theta360.plugin.ACTION_AUDIO_WARNING");

    private final String value;

    PresetSound(String value) {
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
