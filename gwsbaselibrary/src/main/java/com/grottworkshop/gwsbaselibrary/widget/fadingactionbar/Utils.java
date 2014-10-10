package com.grottworkshop.gwsbaselibrary.widget.fadingactionbar;

import android.content.Context;
import android.view.WindowManager;

/**
 *
 * Created by fgrott on 10/9/2014.
 */
public class Utils {
    public static int getDisplayHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        @SuppressWarnings("deprecation")
        int displayHeight = wm.getDefaultDisplay().getHeight();
        return displayHeight;
    }
}
