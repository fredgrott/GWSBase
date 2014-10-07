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
import android.view.LayoutInflater;

/**
 * Created by fgrott on 10/7/2014.
 */
class CalligraphyLayoutInflater extends LayoutInflater {

    private final int mAttributeId;

    protected CalligraphyLayoutInflater(Context context, int attributeId) {
        super(context);
        mAttributeId = attributeId;
        setUpLayoutFactory();
    }

    protected CalligraphyLayoutInflater(LayoutInflater original, Context newContext, int attributeId) {
        super(original, newContext);
        mAttributeId = attributeId;
        setUpLayoutFactory();
    }

    private void setUpLayoutFactory() {
        if (!(getFactory() instanceof CalligraphyFactory)) {
            setFactory(new CalligraphyFactory(getFactory(), mAttributeId));
        }
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new CalligraphyLayoutInflater(this, newContext, mAttributeId);
    }
}
