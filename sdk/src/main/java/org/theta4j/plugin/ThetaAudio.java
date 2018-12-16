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

public final class ThetaAudio {
    private ThetaAudio() {
        throw new AssertionError("unreachable");
    }

    public static final String RIC_MIC_ENABLE_B_FORMAT = "RicUseBFormat=true";

    public static final String RIC_MIC_DISABLE_B_FORMAT = "RicUseBFormat=false";

    public static final String RIC_MIC_SELECT_AUTO = "RicMicSelect=RicMicSelectAuto";

    public static final String RIC_MIC_SELECT_INTERNAL = "RicMicSelect=RicMicSelectInternal";

    public static final String RIC_MIC_SELECT_EXTERNAL = "RicMicSelect=RicMicSelectExternal";

    public static final String RIC_MIC_SURROUND_VOLUME_LEVEL_NORMAL = "RicMicSurrondVolumeLevel=RicMicSurrondVolumeLevelNorma";

    public static final String RIC_MIC_SURROUND_VOLUME_LEVEL_LARGE = "RicMicSurrondVolumeLevel=RicMicSurrondVolumeLevelLarge";
}
