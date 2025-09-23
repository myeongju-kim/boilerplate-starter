package org.codegen.utils;

import java.util.*;
import org.codegen.generator.CodegenInputs;

public final class ArgsParser {

    private ArgsParser() {
    }

    public static CodegenInputs parse(String[] args) {
        if (args == null) {
            args = new String[0];
        }

        Map<String, String> kv = toKeyValueMap(args);

        if (hasTrue(kv, "help") || hasTrue(kv, "h")) {
            printUsageAndExit();
        }

        String jdbcUrl = kv.getOrDefault("jdbcurl", kv.get("jdbcUrl"));
        String basePackage = kv.getOrDefault("basepackage", kv.get("basePackage"));
        if (isBlank(jdbcUrl) || isBlank(basePackage)) {
            System.err.println("[codegen] Missing required args: jdbcUrl, basePackage");
            printUsageAndExit();
        }

        return new CodegenInputs(jdbcUrl, firstNonBlank(kv.get("username"), kv.get("user")), firstNonBlank(kv.get("password"), kv.get("pass")),
            basePackage, firstNonBlank(kv.get("outputdir"), kv.get("outputDir")));
    }

    // --- helpers ---

    private static Map<String, String> toKeyValueMap(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < args.length; i++) {
            String a = args[i];

            if (a == null) {
                continue;
            }
            a = a.trim();
            if (a.isEmpty()) {
                continue;
            }

            if (a.startsWith("--")) {
                a = a.substring(2);
            } else if (a.startsWith("-")) {
                a = a.substring(1);
            } else {
                // skip non-flag token
                continue;
            }

            String key;
            String value;

            int eq = a.indexOf('=');
            if (eq >= 0) {
                key = a.substring(0, eq).trim();
                value = a.substring(eq + 1).trim();
            } else {
                key = a.trim();
                // next token as value if present and not another flag
                if ((i + 1) < args.length && !isFlag(args[i + 1])) {
                    value = args[++i].trim();
                } else {
                    // boolean-style flag (e.g., --help)
                    value = "true";
                }
            }

            if (!key.isEmpty()) {
                map.put(keyNormalize(key), value);
            }
        }
        return map;
    }

    private static boolean isFlag(String s) {
        if (s == null) {
            return false;
        }
        s = s.trim();
        return s.startsWith("-") || s.startsWith("--");
    }

    private static String keyNormalize(String k) {
        // normalize camel/snake/mixed to lower without separators for easier lookup
        return k.replace("-", "")
            .replace("_", "")
            .toLowerCase(Locale.ROOT);
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static String firstNonBlank(String... vals) {
        if (vals == null) {
            return null;
        }
        for (String v : vals) {
            if (!isBlank(v)) {
                return v;
            }
        }
        return null;
    }

    private static boolean hasTrue(Map<String, String> kv, String key) {
        String v = kv.get(keyNormalize(key));
        return v != null && (v.equalsIgnoreCase("true") || v.isEmpty());
    }

    private static void printUsageAndExit() {
        String usage = """
            Usage:
              java cCodegenCli \\
                --jdbcUrl=<JDBC URL> \\
                --basePackage=<base.package> \\
                [--username=<db user>] \\
                [--password=<db pass>] \\
                [--outputDir=<custom output dir>] \\
                [--generationTypes=CONTROLLER,SERVICE,MAPPER,ENTITY] \\
                [--help]
            
            Notes:
              - If outputDir is omitted, CodegenExecutor writes under:
                    src/main/java/<basePackage path>/...
              - generationTypes is CSV; defaults to CONTROLLER,SERVICE,MAPPER,ENTITY when omitted.
              - Supported arg forms: --key=value | --key value | -key=value | -key value
            """;
        System.out.println(usage);
        throw new IllegalArgumentException("Invalid or missing arguments.");
    }
}
