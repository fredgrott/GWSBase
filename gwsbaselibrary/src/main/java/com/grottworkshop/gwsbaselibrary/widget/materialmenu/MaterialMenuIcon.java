/*
 * Copyright (C) 2014 Balys Valentukevicius
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
package com.grottworkshop.gwsbaselibrary.widget.materialmenu;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;

import static com.grottworkshop.gwsbaselibrary.widget.materialmenu.MaterialMenuDrawable.DEFAULT_PRESSED_DURATION;
import static com.grottworkshop.gwsbaselibrary.widget.materialmenu.MaterialMenuDrawable.DEFAULT_SCALE;
import static com.grottworkshop.gwsbaselibrary.widget.materialmenu.MaterialMenuDrawable.DEFAULT_TRANSFORM_DURATION;


/**
 *
 *
 * Created by fgrott on 10/9/2014.
 */
public  class MaterialMenuIcon extends MaterialMenuBase {

    private MaterialMenuDrawable drawable;

    /**
     * Instantiates a new Material menu icon.
     *
     * @param activity the activity
     * @param color the color
     * @param stroke the stroke
     */
    public MaterialMenuIcon(Activity activity, int color, MaterialMenuDrawable.Stroke stroke) {
        this(activity, color, stroke, DEFAULT_TRANSFORM_DURATION, DEFAULT_PRESSED_DURATION);
    }

    /**
     * Instantiates a new Material menu icon.
     *
     * @param activity the activity
     * @param color the color
     * @param stroke the stroke
     * @param transformDuration the transform duration
     */
    public MaterialMenuIcon(Activity activity, int color, MaterialMenuDrawable.Stroke stroke, int transformDuration) {
        this(activity, color, stroke, transformDuration, DEFAULT_PRESSED_DURATION);
    }

    /**
     * Instantiates a new Material menu icon.
     *
     * @param activity the activity
     * @param color the color
     * @param stroke the stroke
     * @param transformDuration the transform duration
     * @param pressedDuration the pressed duration
     */
    public MaterialMenuIcon(Activity activity, int color, MaterialMenuDrawable.Stroke stroke, int transformDuration, int pressedDuration) {
        drawable = new MaterialMenuDrawable(activity, color, stroke, DEFAULT_SCALE, transformDuration, pressedDuration);
        setupActionBar(activity);
    }

    @TargetApi(14)
    private void setupActionBar(Activity activity) {
        Resources resources = activity.getResources();
        int id = resources.getIdentifier("android:id/home", null, null);
        View view = activity.getWindow().getDecorView().findViewById(id);
        if (view == null) throw new IllegalStateException("Could not find ActionBar icon view");

        // need no margins so that clicked state would work nicely
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.bottomMargin = 0;
        params.topMargin = 0;

        ActionBar actionBar = activity.getActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setIcon(drawable);
    }

    @Override
    public MaterialMenuDrawable getDrawable() {
        return drawable;
    }
}
