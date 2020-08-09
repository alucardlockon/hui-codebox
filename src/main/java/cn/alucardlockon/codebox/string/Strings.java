package cn.alucardlockon.codebox.string;

import cn.alucardlockon.codebox.core.Langs;

/**
 * String utils
 */
public class Strings {

    public static String upperFirst(CharSequence str) {
        if (Langs.isEmpty(str)) {
            return "";
        }
        if (str.length() > 0) {
            char firstChar = str.charAt(0);
            if (Character.isLowerCase(firstChar)) {
                return Character.toUpperCase(firstChar) + slice(str, 1, -1);
            }
        }
        return str.toString();
    }

    public static String lowerFirst(CharSequence str) {
        if (Langs.isEmpty(str)) {
            return "";
        }
        if (str.length() > 0) {
            char firstChar = str.charAt(0);
            if (Character.isUpperCase(firstChar)) {
                return Character.toLowerCase(firstChar) + slice(str, 1, -1);
            }
        }
        return str.toString();
    }

    public static String camelCase(CharSequence str) {
        if (Langs.isEmpty(str)) {
            return "";
        }
        String str2 = str.toString();
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < str2.length(); i++) {
            char c = str2.charAt(i);

            if (c == '_') {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String snakeCase(CharSequence str) {
        if (Langs.isEmpty(str)) {
            return "";
        }
        String str2 = str.toString();
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (int i = 0; i < str2.length(); i++) {
            char c = str2.charAt(i);

            if (Character.isUpperCase(c) && !isFirst) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(c));
            if (isFirst)
                isFirst = false;
        }
        return sb.toString();
    }

    public static String slice(CharSequence str, int beginIndex, int endIndex) {
        if (Langs.isEmpty(str)) {
            return "";
        }

        if (beginIndex < 0)
            beginIndex = 0;
        if (endIndex < 0 || endIndex > str.length())
            endIndex = str.length();

        StringBuilder sb = new StringBuilder();
        for (int i = beginIndex; i < endIndex; i++) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}
