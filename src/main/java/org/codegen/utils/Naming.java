package org.codegen.utils;


public class Naming {

    /**
     * USER_ACCOUNT -> UserAccount
     */
    public static String className(String tableName) {
        String s = tableName.trim();
        s = s.replace('-', '_');
        String[] parts = s.split("_");
        StringBuilder out = new StringBuilder();
        for (String p : parts) {
            if (p.isEmpty()) {
                continue;
            }
            String lower = p.toLowerCase();
            out.append(Character.toUpperCase(lower.charAt(0)))
                .append(lower.substring(1));
        }
        return out.toString();
    }

    /**
     * USER_NAME -> userName
     */
    public static String varName(String tableName) {
        String s = tableName.trim();
        s = s.replace('-', '_');
        String[] parts = s.split("_");
        StringBuilder out = new StringBuilder();
        boolean first = true;
        for (String p : parts) {
            if (p.isEmpty()) {
                continue;
            }
            String lower = p.toLowerCase();
            if (first) {
                out.append(lower);
                first = false;
            } else {
                out.append(Character.toUpperCase(lower.charAt(0)))
                    .append(lower.substring(1));
            }
        }
        return out.toString();
    }

    /**
     * USER_ACCOUNT -> useraccount (패키지 세그먼트)
     */
    public static String packageName(String tableName) {
        return tableName.toLowerCase()
            .replace("_", "")
            .replace("-", "");
    }

    /**
     * USER_ACCOUNT -> user-accounts (kebab + 복수)
     */
    public static String pathName(String tableName) {
        String seg = tableName.toLowerCase().replace('_', '-').replace(' ', '-');
        return Pluralize.pluralize(seg);
    }
}
