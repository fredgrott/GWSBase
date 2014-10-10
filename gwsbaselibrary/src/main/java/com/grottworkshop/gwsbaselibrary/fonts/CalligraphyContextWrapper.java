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

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;

/**
 *
 *
 * Created by fgrott on 10/7/2014.
 */
public class CalligraphyContextWrapper extends ContextWrapper {

    private LayoutInflater mInflater;

    private final int mAttributeId;

    /**
     * Uses the default configuration from {@link CalligraphyConfig}
     *
     * Remember if you are defining default in the
     * {@link CalligraphyConfig} make sure this is initialised before
     * the activity is created.
     *
     * @param base ContextBase to Wrap
     */
    public CalligraphyContextWrapper(Context base) {
        super(base);
        mAttributeId = CalligraphyConfig.get().getAttrId();
    }

    /**
     * Override the default AttributeId, this will always take the custom attribute defined here
     * and ignore the one set in {@link CalligraphyConfig}.
     *
     * Remember if you are defining default in the
     * {@link CalligraphyConfig} make sure this is initialised before
     * the activity is created.
     *
     * @param base ContextBase to Wrap
     * @param attributeId Attribute to lookup.
     */
    public CalligraphyContextWrapper(Context base, int attributeId) {
        super(base);
        mAttributeId = attributeId;
    }

    @Override
    public Object getSystemService(String name) {
        if (LAYOUT_INFLATER_SERVICE.equals(name)) {
            if (mInflater == null) {
                mInflater = new CalligraphyLayoutInflater(LayoutInflater.from(getBaseContext()), this, mAttributeId);
            }
            return mInflater;
        }
        return super.getSystemService(name);
    }

}
