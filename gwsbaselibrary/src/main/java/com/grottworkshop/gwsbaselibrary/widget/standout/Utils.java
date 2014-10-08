package com.grottworkshop.gwsbaselibrary.widget.standout;

/**
 * Created by fgrott on 10/8/2014.
 */
public class Utils {
    public static boolean isSet(int flags, int flag) {
        return (flags & flag) == flag;
    }
}
