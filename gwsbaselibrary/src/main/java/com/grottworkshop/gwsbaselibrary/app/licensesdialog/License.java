/*
 * Copyright 2013 Philip Schiffer
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.grottworkshop.gwsbaselibrary.app.licensesdialog;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 *
 *
 * Created by fgrott on 10/8/2014.
 */
public abstract class License implements Serializable {

    private static final long serialVersionUID = 3100331505738956523L;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private String mCachedSummaryText = null;
    private String mCachedFullText = null;

    /**
     * Gets name.
     *
     * @return the name
     */
    public abstract String getName();

    /**
     * Read summary text from resources.
     *
     * @param context the context
     * @return the string
     */
    public abstract String readSummaryTextFromResources(final Context context);

    /**
     * Read full text from resources.
     *
     * @param context the context
     * @return the string
     */
    public abstract String readFullTextFromResources(final Context context);

    /**
     * Gets version.
     *
     * @return the version
     */
    public abstract String getVersion();

    /**
     * Gets url.
     *
     * @return the url
     */
    public abstract String getUrl();

    //

    /**
     * Gets summary text.
     *
     * @param context the context
     * @return the summary text
     */
    public final String getSummaryText(final Context context) {
        if (mCachedSummaryText == null) {
            mCachedSummaryText = readSummaryTextFromResources(context);
        }

        return mCachedSummaryText;
    }

    /**
     * Gets full text.
     *
     * @param context the context
     * @return the full text
     */
    public final String getFullText(final Context context) {
        if (mCachedFullText == null) {
            mCachedFullText = readFullTextFromResources(context);
        }

        return mCachedFullText;
    }

    /**
     * Gets content.
     *
     * @param context the context
     * @param contentResourceId the content resource id
     * @return the content
     */
    protected String getContent(final Context context, final int contentResourceId) {
        BufferedReader reader = null;
        try {
            final InputStream inputStream = context.getResources().openRawResource(contentResourceId);
            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));
                return toString(reader);
            }
            throw new IOException("Error opening license file.");
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    // Don't care.
                }
            }
        }
    }

    private String toString(final BufferedReader reader) throws IOException {
        final StringBuilder builder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            builder.append(line).append(LINE_SEPARATOR);
        }
        return builder.toString();
    }

}
