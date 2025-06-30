package com.jlgdev.ceres.models.Utils;

import java.text.Normalizer;

public class TextUtils {
    
    public static String removeAccents(String input) {
        if (input == null)
            return null;
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        normalized = normalized.replace("œ", "oe").replace("Œ", "Oe")
                .replace("æ", "ae").replace("Æ", "Ae");

        return normalized;
    }
}
