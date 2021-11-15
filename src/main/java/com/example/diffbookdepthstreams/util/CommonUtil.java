package com.example.diffbookdepthstreams.util;

public class CommonUtil {
    public static String formatUrl(String url, Object... params) {
        return String.format(url, params);
    }
}
