package com.grottworkshop.gwsbaselibrary.activityanimationshardway;

import android.os.Bundle;

/**
 * Created by fgrott on 10/10/2014.
 */
public class DoorActivity extends AnimatedDoorActivity {

    @Override
    protected int layoutResId() {
        return R.layout.activity_door;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
