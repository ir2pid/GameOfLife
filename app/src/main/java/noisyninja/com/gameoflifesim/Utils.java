package noisyninja.com.gameoflifesim;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import noisyninja.com.gameoflifesim.components.GameCell;

/**
 * General utility class
 * Created by ir2pid on 29/02/16.
 */
public class Utils {


    public static String getResString(Context context,@StringRes int resourceId) {
        return context.getResources().getString(resourceId);
    }

    public static int getResInt(Context context,@IntegerRes int resourceId) {
        return context.getResources().getInteger(resourceId);
    }

    public static int getResColor(Context context,@ColorRes int resourceId) {
        return context.getResources().getColor(resourceId);
    }

    public static Point getScreenDimention(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static void logD(Class clazz, String s) {
        Log.d(clazz.getSimpleName(), s);
    }

}
