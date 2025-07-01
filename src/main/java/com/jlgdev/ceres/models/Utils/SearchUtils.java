package com.jlgdev.ceres.models.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchUtils {

    public static boolean isInIterable(Iterable<String> haystack, String needle) {
        if (haystack == null || needle == null) {
            return false;
        }
        for (String hay : haystack) {

            Pattern pattern = Pattern.compile("(.*(\\s|\'))?\\b" + TextUtils.removeAccents(hay) + "(s|x)?\\b(\\s.*)?");
            Matcher matcher = pattern.matcher(TextUtils.removeAccents(needle));
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
