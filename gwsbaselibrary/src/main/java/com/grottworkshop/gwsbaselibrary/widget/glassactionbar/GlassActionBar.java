/*
 * Copyright (C) 2013 Manuel Peinado
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

package com.grottworkshop.gwsbaselibrary.widget.glassactionbar;

/**
 *
 *
 * Created by fgrott on 10/9/2014.
 */
public class GlassActionBar {
    /**
     * The constant verbose.
     */
    public static boolean verbose = false;

    /**
     * The constant DEFAULT_BLUR_RADIUS.
     */
    public static final int DEFAULT_BLUR_RADIUS = 7;
    /**
     * The constant MIN_BLUR_RADIUS.
     */
    public static final int MIN_BLUR_RADIUS = 1;
    /**
     * The constant MAX_BLUR_RADIUS.
     */
    public static final int MAX_BLUR_RADIUS = 20;

    /**
     * The constant DEFAULT_DOWNSAMPLING.
     */
    public static final int DEFAULT_DOWNSAMPLING = 5;
    /**
     * The constant MIN_DOWNSAMPLING.
     */
    public static final int MIN_DOWNSAMPLING = 1;
    /**
     * The constant MAX_DOWNSAMPLING.
     */
    public static final int MAX_DOWNSAMPLING = 6;

    /**
     * Is valid blur radius.
     *
     * @param value the value
     * @return the boolean
     */
    public static boolean isValidBlurRadius(int value) {
        return value >= MIN_BLUR_RADIUS && value <= MAX_BLUR_RADIUS;
    }

    /**
     * Is valid downsampling.
     *
     * @param value the value
     * @return the boolean
     */
    public static boolean isValidDownsampling(int value) {
        return value >= MIN_DOWNSAMPLING && value <= MAX_DOWNSAMPLING;
    }
}