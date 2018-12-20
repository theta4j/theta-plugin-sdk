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

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;

import java.util.List;
import java.util.Locale;

public abstract class ThetaPluginActivity extends Activity {
    private static final String TAG = "THETA_PLUGIN_SDK";

    private final BroadcastReceiver mKeyEventReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            final int keyCode = intent.getIntExtra(ThetaIntent.EXTRA_KEY_CODE, -1);
            final KeyEvent keyEvent = intent.getParcelableExtra(ThetaIntent.EXTRA_KEY_EVENT);

            dispatchKeyEvent(keyEvent);

            if (ThetaIntent.ACTION_KEY_DOWN.equals(action)) {
                onKeyDown(keyCode, keyEvent);
                if (keyEvent.isLongPress()) {
                    onKeyLongPress(keyCode, keyEvent);
                    if (keyCode == ThetaIntent.KEY_CODE_MODE) {
                        onFinishPluginRequested();
                    }
                }
            } else if (ThetaIntent.ACTION_KEY_UP.equals(action)) {
                onKeyUp(keyCode, keyEvent);
            } else {
                throw new AssertionError("unexpected action: " + action);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            Log.e(TAG, "Uncaught Exception", throwable);
            final String message = String.format(Locale.US,
                    "Uncaught Exception thread:%s, message:%s", thread.getName(), throwable.toString());
            finishPlugin(ExitStatus.FAILURE, message);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        final IntentFilter keyEventFilter = new IntentFilter();
        keyEventFilter.addAction(ThetaIntent.ACTION_KEY_DOWN);
        keyEventFilter.addAction(ThetaIntent.ACTION_KEY_UP);
        registerReceiver(mKeyEventReceiver, keyEventFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mKeyEventReceiver);
    }

    // Key Event

    /**
     * Called when a mode button long pressed.
     * Default implementation finishes plugin.
     * Override this method if you need custom cleanup actions.
     */
    protected void onFinishPluginRequested() {
        openMainCamera();
        finishPlugin(ExitStatus.SUCCESS, "OK");
    }

    // Controlling LEDs

    public final void showLED(@NonNull LEDTarget target) {
        final Intent intent = new Intent(ThetaIntent.ACTION_LED_SHOW);
        intent.putExtra(ThetaIntent.EXTRA_LED_TARGET, target.getValue());
        sendBroadcast(intent);
    }

    public final void showLED(@NonNull LEDTarget target, @NonNull LEDColor color) {
        final Intent intent = new Intent(ThetaIntent.ACTION_LED_SHOW);
        intent.putExtra(ThetaIntent.EXTRA_LED_TARGET, target.getValue());
        intent.putExtra(ThetaIntent.EXTRA_LED_COLOR, color.getValue());
        sendBroadcast(intent);
    }

    public final void blinkLED(@NonNull LEDTarget target) {
        final Intent intent = new Intent(ThetaIntent.ACTION_LED_BLINK);
        intent.putExtra(ThetaIntent.EXTRA_LED_TARGET, target.getValue());
        sendBroadcast(intent);
    }

    public final void blinkLED(@NonNull LEDTarget target, @NonNull LEDColor color) {
        final Intent intent = new Intent(ThetaIntent.ACTION_LED_BLINK);
        intent.putExtra(ThetaIntent.EXTRA_LED_TARGET, target.getValue());
        intent.putExtra(ThetaIntent.EXTRA_LED_COLOR, color.getValue());
        sendBroadcast(intent);
    }

    public final void blinkLED(@NonNull LEDTarget target, @NonNull LEDColor color, @IntRange(from = 1, to = 2000) int period) {
        final Intent intent = new Intent(ThetaIntent.ACTION_LED_BLINK);
        intent.putExtra(ThetaIntent.EXTRA_LED_TARGET, target.getValue());
        intent.putExtra(ThetaIntent.EXTRA_LED_COLOR, color.getValue());
        intent.putExtra(ThetaIntent.EXTRA_LED_PERIOD, period);
        sendBroadcast(intent);
    }

    public final void hideLED(@NonNull LEDTarget target) {
        final Intent intent = new Intent(ThetaIntent.ACTION_LED_HIDE);
        intent.putExtra(ThetaIntent.EXTRA_LED_TARGET, target.getValue());
        sendBroadcast(intent);
    }

    // Controlling Speaker

    public final void ring(@NonNull PresetSound sound) {
        sendBroadcast(new Intent(sound.getValue()));
    }

    // Controlling Wi-Fi

    public final void setWLanMode(@NonNull WLanMode mode) {
        sendBroadcast(new Intent(mode.getValue()));
    }

    // Updating the Database

    public final void updateDatabase(@NonNull List<String> targets) {
        final Intent intent = new Intent(ThetaIntent.ACTION_DATABASE_UPDATE);
        intent.putExtra(ThetaIntent.EXTRA_DATABASE_TARGETS, targets.toArray(new String[0]));
        sendBroadcast(intent);
    }

    // Notifying Occurrences of Errors

    public final void notifyError() {
        sendBroadcast(new Intent(ThetaIntent.ACTION_ERROR_OCCURED));
    }

    // Notifying Completion of Plug-in

    public final void finishPlugin(@NonNull String message) {
        final Intent intent = new Intent(ThetaIntent.ACTION_PLUGIN_FINISH);
        intent.putExtra(ThetaIntent.EXTRA_PLUGIN_PACKAGE_NAME, getPackageName());
        intent.putExtra(ThetaIntent.EXTRA_PLUGIN_MESSAGE, message);
        sendBroadcast(intent);
        finishAndRemoveTask();
    }

    public final void finishPlugin(@NonNull ExitStatus status, @NonNull String message) {
        final Intent intent = new Intent(ThetaIntent.ACTION_PLUGIN_FINISH);
        intent.putExtra(ThetaIntent.EXTRA_PLUGIN_PACKAGE_NAME, getPackageName());
        intent.putExtra(ThetaIntent.EXTRA_PLUGIN_EXIT_STATUS, status.getValue());
        intent.putExtra(ThetaIntent.EXTRA_PLUGIN_MESSAGE, message);
        sendBroadcast(intent);
        finishAndRemoveTask();
    }

    // Notifying Camera Device Control

    public final void closeMainCamera() {
        sendBroadcast(new Intent(ThetaIntent.ACTION_MAIN_CAMERA_CLOSE));
    }

    public final void openMainCamera() {
        sendBroadcast(new Intent(ThetaIntent.ACTION_MAIN_CAMERA_OPEN));
    }
}
