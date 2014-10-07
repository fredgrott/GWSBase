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

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

import java.util.HashMap;

/**
 * A helper loading {@link android.graphics.Typeface} avoiding the leak of the font when loaded
 * by multiple calls to {@link android.graphics.Typeface#createFromAsset(android.content.res.AssetManager, String)}
 * on pre-ICS versions.
 * <p>
 * More details can be found here https://code.google.com/p/android/issues/detail?id=9904
 * <p>
 * Created by fgrott on 10/7/2014.
 */
public final class TypefaceUtils {

    private static final HashMap<String, Typeface> sCachedFonts = new HashMap<String, Typeface>();
    private static final HashMap<Typeface, CalligraphyTypefaceSpan> sCachedSpans = new HashMap<Typeface, CalligraphyTypefaceSpan>();

    /**
     * A helper loading a custom font.
     *
     * @param assetManager App's asset manager.
     * @param filePath     The path of the file.
     * @return Return {@link android.graphics.Typeface} or null if the path is invalid.
     */
    public static Typeface load(final AssetManager assetManager, final String filePath) {
        synchronized (sCachedFonts) {
            try {
                if (!sCachedFonts.containsKey(filePath)) {
                    final Typeface typeface = Typeface.createFromAsset(assetManager, filePath);
                    sCachedFonts.put(filePath, typeface);
                    return typeface;
                }
            } catch (Exception e) {
                Log.w("Calligraphy", "Can't create asset from " + filePath + ". Make sure you have passed in the correct path and file name.", e);
                sCachedFonts.put(filePath, null);
                return null;
            }
            return sCachedFonts.get(filePath);
        }
    }

    /**
     * A helper loading custom spans so we don't have to keep creating hundreds of spans.
     *
     * @param typeface not null typeface
     * @return will return null of typeface passed in is null.
     */
    public static CalligraphyTypefaceSpan getSpan(final Typeface typeface) {
        if (typeface == null) return null;
        synchronized (sCachedSpans) {
            if (!sCachedSpans.containsKey(typeface)) {
                final CalligraphyTypefaceSpan span = new CalligraphyTypefaceSpan(typeface);
                sCachedSpans.put(typeface, span);
                return span;
            }
            return sCachedSpans.get(typeface);
        }
    }

    private TypefaceUtils() {
    }
}
