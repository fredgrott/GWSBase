/*
   Copyright 2013 Christopher Jenkins, Modified by Fred Grott(GrottWorkShop) Copyright 2014

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package com.grottworkshop.gwsbaselibrary.fonts;

import android.text.TextUtils;

/**
 *
 *
 * Created by fgrott on 10/7/2014.
 */
public class CalligraphyConfig {

    private static CalligraphyConfig mInstance;

    /**
     * Init the Calligraphy Config file. Each time you call this you set a new default. Of course setting this multiple
     * times during runtime could have undesired effects.
     *
     * @param defaultFontAssetPath a path to a font file in the assets folder, e.g. "fonts/roboto-light.ttf",
     *                             passing null will default to the device font-family.
     */
    public static void initDefault(String defaultFontAssetPath) {
        mInstance = new CalligraphyConfig(defaultFontAssetPath);
    }

    /**
     * Init only the custom attribute to lookup.
     *
     * @param defaultAttributeId the custom attribute to look for.
     * @see #initDefault(String, int)
     */
    public static void initDefault(int defaultAttributeId) {
        mInstance = new CalligraphyConfig(defaultAttributeId);
    }

    /**
     * Define the default font and the custom attribute to lookup globally.
     *
     * @param defaultFontAssetPath path to a font file in the assets folder, e.g. "fonts/Roboto-light.ttf",
     * @param defaultAttributeId   the custom attribute to look for.
     * @see #initDefault(String)
     * @see #initDefault(int)
     */
    public static void initDefault(String defaultFontAssetPath, int defaultAttributeId) {
        mInstance = new CalligraphyConfig(defaultFontAssetPath, defaultAttributeId);
    }

    static CalligraphyConfig get() {
        if (mInstance == null)
            mInstance = new CalligraphyConfig();
        return mInstance;
    }


    private final String mFontPath;
    private final boolean mIsFontSet;
    private final int mAttrId;

    private CalligraphyConfig() {
        this(null, -1);
    }

    private CalligraphyConfig(int attrId) {
        this(null, attrId);
    }

    private CalligraphyConfig(String defaultFontAssetPath) {
        this(defaultFontAssetPath, -1);
    }

    private CalligraphyConfig(String defaultFontAssetPath, int attrId) {
        this.mFontPath = defaultFontAssetPath;
        mIsFontSet = !TextUtils.isEmpty(defaultFontAssetPath);
        mAttrId = attrId != -1 ? attrId : -1;
    }

    /**
     * @return mFontPath for text views might be null
     */
    String getFontPath() {
        return mFontPath;
    }

    /**
     * @return true if set, false if null|empty
     */
    boolean isFontSet() {
        return mIsFontSet;
    }

    /**
     * @return the custom attrId to look for, -1 if not set.
     */
    public int getAttrId() {
        return mAttrId;
    }
}
