/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.android.telecomm;

import android.provider.Settings;
import android.telecomm.CallServiceProvider;
import android.telecomm.CallServiceSelector;

/**
 * A helper class which serves only to make it easier to lookup timeout values. This class should
 * never be instantiated, and only accessed through the {@link #get(String, long)} method.
 *
 * These methods are safe to call from any thread, including the UI thread.
 */
public final class Timeouts {
    /** A prefix to use for all keys so to not clobber the global namespace. */
    private static final String PREFIX = "telecomm.";

    private Timeouts() {}

    /**
     * Returns the timeout value from Settings or the default value if it hasn't been changed. This
     * method is safe to call from any thread, including the UI thread.
     *
     * @param key Settings key to retrieve.
     * @param defaultValue Default value, in milliseconds.
     * @return The timeout value from Settings or the default value if it hasn't been changed.
     */
    private static long get(String key, long defaultValue) {
        return Settings.Secure.getLong(
                TelecommApp.getInstance().getContentResolver(), PREFIX + key, defaultValue);
    }

    /**
     * @return The longest period in milliseconds each {@link CallServiceProvider} lookup cycle is
     *     allowed to span over.
     */
    public static long getProviderLookupMs() {
        return get("provider_lookup_ms", 1000);
    }

    /**
     * @return How frequently, in milliseconds, to run {@link Switchboard}'s clean-up "tick" cycle.
     */
    public static long getTickMs() {
        return get("tick_ms", 250);
    }

    /**
     * Returns the longest period, in milliseconds, each new outgoing call is allowed to wait before
     * being established. If the call does not connect before this time, abort the call.
     *
     * @return The longest period, in milliseconds, each new call is allowed to wait before being
     *     established.
     */
    public static long getNewOutgoingCallMs() {
        return get("new_outgoing_call_ms", 5000);
    }
}