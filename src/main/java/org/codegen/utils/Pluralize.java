package org.codegen.utils;

import java.util.HashMap;
import java.util.Map;

public class Pluralize {

    private static final Map<String, String> irregulars = new HashMap<>();
    static {
        irregulars.put("person", "people");
        irregulars.put("man", "men");
        irregulars.put("woman", "women");
        irregulars.put("child", "children");
        irregulars.put("tooth", "teeth");
        irregulars.put("foot", "feet");
        irregulars.put("mouse", "mice");
        irregulars.put("goose", "geese");
    }

    public static String pluralize(String word) {
        if (word == null || word.isBlank()) return word;

        String lower = word.toLowerCase();

        // irregular 체크
        if (irregulars.containsKey(lower)) {
            return irregulars.get(lower);
        }

        // 규칙 적용
        if (lower.endsWith("y") && lower.length() > 1 && isConsonant(lower.charAt(lower.length() - 2))) {
            // city -> cities
            return lower.substring(0, lower.length() - 1) + "ies";
        } else if (lower.endsWith("s") || lower.endsWith("x") || lower.endsWith("z")
            || lower.endsWith("ch") || lower.endsWith("sh")) {
            // bus -> buses, box -> boxes
            return lower + "es";
        } else {
            // 기본은 그냥 s 붙이기
            return lower + "s";
        }
    }

    private static boolean isConsonant(char c) {
        return "aeiou".indexOf(Character.toLowerCase(c)) == -1;
    }
}
