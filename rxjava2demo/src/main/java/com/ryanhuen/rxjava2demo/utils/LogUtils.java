
package com.ryanhuen.rxjava2demo.utils;

import android.widget.TextView;

/**
 * Created by ryanhuen on 18-4-28.
 */

public class LogUtils {

    public static void log2ConsoleOuputView(TextView view, String text) {
        String tmp = view.getText().toString() + "\n";
        tmp += text;
        view.setText(tmp);
    }
}
