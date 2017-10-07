package site.nebulas.demo.utils;

import android.widget.Toast;
import site.nebulas.demo.aplication.MainApplication;

/**
 * Created by Nebula on 2017/9/30.
 */

public class ToastUtil {
    private static Toast toast;
    private static String oldMsg = "";

    public static void toast (String message) {
        if (toast != null) {
            if (oldMsg.equals(message)) {
                toast.setText(message);
                toast.show();
            } else {
                oldMsg = message;
                toast = Toast.makeText(MainApplication.getContext(), "", Toast.LENGTH_SHORT);
                toast.setText(message);
                toast.show();
            }
        } else {
            oldMsg = message;
            toast = Toast.makeText(MainApplication.getContext(), "", Toast.LENGTH_SHORT);
            toast.setText(message);
            toast.show();
        }
    }
}
