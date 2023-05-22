/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.xray.entities;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @deprecated For internal use only.
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@Deprecated
public class StringValidator {

    @EnsuresNonNullIf(expression = "#1", result = true)
    public static boolean isNotNullOrBlank(@Nullable String string) {
        return string != null && !string.trim().isEmpty();
    }

    @EnsuresNonNullIf(expression = "#1", result = true)
    public static boolean isNotNullOrBlankFast(@Nullable String string) {
        if (string == null) {
            return false;
        }
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isWhitespace(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    @EnsuresNonNullIf(expression = "#1", result = false)
    public static boolean isNullOrBlank(@Nullable String string) {
        return string == null || string.trim().isEmpty();
    }

    @EnsuresNonNullIf(expression = "#1", result = false)
    public static boolean isNullOrBlankFast(@Nullable String string) {
        if (string == null) {
            return true;
        }
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isWhitespace(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void throwIfNullOrBlank(@Nullable String string, String validationErrorMessage) {
        if (isNullOrBlankFast(string)) {
            throw new RuntimeException(validationErrorMessage);
        }
    }

}
