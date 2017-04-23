package com.epam.ws_socet.utils;

public class SplitUtils {
    public static String getLastSplitValueBy(String sourceString, String patternForSplit) {
        String lastValue = "";
        String[] content = sourceString.split(patternForSplit);

        if (content.length > 1) {
            lastValue = content[content.length - 1].trim();
        }
        return lastValue;
    }

    public static String getCertainSplitValueBy(String sourceString, int splitIndex, String patternForSplit) {
        String lastValue = "";
        String[] content = sourceString.split(patternForSplit);

        if (content.length > 1) {
            lastValue = content[splitIndex].trim();
        }
        return lastValue;
    }
}
