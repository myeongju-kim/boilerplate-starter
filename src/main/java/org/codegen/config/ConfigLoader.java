package org.codegen.config;

import org.gradle.api.Project;
import org.yaml.snakeyaml.Yaml;
import org.codegen.generator.CodegenInputs;
import org.codegen.generator.GenerationType;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public final class ConfigLoader {
    private ConfigLoader() {}

    public static CodegenInputs load(Project project) throws IOException {
        // 1) base 파일 탐색
        File baseProps = firstExisting(project,
            "src/main/resources/application.properties", "application.properties");
        File baseYaml = firstExisting(project,
            "src/main/resources/application.yml", "src/main/resources/application.yaml",
            "application.yml", "application.yaml");
        if (baseProps == null && baseYaml == null) {
            throw new IllegalArgumentException(
                "[codegen] application.(yml|yaml|properties) not found under src/main/resources or project root");
        }

        // 2) active profile
        String profile = firstNonBlank(
            System.getProperty("spring.profiles.active"),
            System.getenv("SPRING_PROFILES_ACTIVE")
        );
        if (profile == null) {
            Map<String, Object> tmp = new LinkedHashMap<>();
            if (baseProps != null) tmp.putAll(loadPropertiesAsMap(baseProps));
            if (baseYaml  != null) tmp.putAll(loadYamlAsMap(baseYaml));
            Object p = tmp.get("spring.profiles.active");
            if (p != null) profile = String.valueOf(p);
        }

        // 3) base 로드
        Map<String, Object> conf = new LinkedHashMap<>();
        if (baseProps != null) conf.putAll(loadPropertiesAsMap(baseProps));
        if (baseYaml  != null) conf.putAll(loadYamlAsMap(baseYaml));

        // 4) profile overlay
        if (!isBlank(profile)) {
            File profProps = firstExisting(project,
                "src/main/resources/application-" + profile + ".properties",
                "application-" + profile + ".properties");
            File profYaml = firstExisting(project,
                "src/main/resources/application-" + profile + ".yml",
                "src/main/resources/application-" + profile + ".yaml",
                "application-" + profile + ".yml",
                "application-" + profile + ".yaml");
            if (profProps != null) conf.putAll(loadPropertiesAsMap(profProps));
            if (profYaml  != null) conf.putAll(loadYamlAsMap(profYaml));
        }

        // 5) codegen.* 읽기
        String jdbcUrl     = firstNonBlank(str(conf.get("codegen.jdbc-url")),  str(conf.get("codegen.jdbcUrl")));
        String username    = firstNonBlank(str(conf.get("codegen.username")));
        String password    = firstNonBlank(str(conf.get("codegen.password")));
        String basePackage = firstNonBlank(str(conf.get("codegen.base-package")), str(conf.get("codegen.basePackage")));
        String outputDir   = firstNonBlank(str(conf.get("codegen.output-dir")), str(conf.get("codegen.outputDir")));

        // spring.datasource.* 대체
        if (isBlank(jdbcUrl)) {
            jdbcUrl = firstNonBlank(str(conf.get("spring.datasource.url")), str(conf.get("spring.datasource.jdbc-url")));
            username = firstNonBlank(username, str(conf.get("spring.datasource.username")));
            password = firstNonBlank(password, str(conf.get("spring.datasource.password")));
        }

        if (isBlank(basePackage)) throw new IllegalArgumentException("[codegen] Missing property: codegen.base-package");
        if (isBlank(jdbcUrl))     throw new IllegalArgumentException("[codegen] Missing property: codegen.jdbc-url (or spring.datasource.url)");

        return new CodegenInputs(jdbcUrl, username, password, basePackage, outputDir);
    }

    // ---------- helpers ----------
    public static String maskPwd(String url) {
        if (isBlank(url)) {
            return "";
        }
        return url.replaceAll("(?i)(password=)[^&]+", "$1****");
    }
    private static File firstExisting(Project p, String... paths) {
        for (String s : paths) {
            File f = p.file(s);
            if (f.exists() && f.isFile()) return f;
        }
        return null;
    }
    private static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
    private static String str(Object o) { return o == null ? null : String.valueOf(o); }
    private static String firstNonBlank(String... vals) {
        if (vals == null) return null;
        for (String v : vals) if (!isBlank(v)) return v;
        return null;
    }

    private static Map<String, Object> loadPropertiesAsMap(File f) throws IOException {
        Properties p = new Properties();
        try (FileInputStream fis = new FileInputStream(f)) { p.load(fis); }
        Map<String, Object> m = new LinkedHashMap<>();
        for (String k : p.stringPropertyNames()) m.put(k, p.getProperty(k));
        return m;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> loadYamlAsMap(File f) throws IOException {
        try (InputStream is = new FileInputStream(f)) {
            Object obj = new Yaml().load(is);
            Map<String, Object> out = new LinkedHashMap<>();
            flatten("", (obj instanceof Map) ? (Map<String, Object>) obj : Map.of(), out);
            return out;
        }
    }
    @SuppressWarnings("unchecked")
    private static void flatten(String prefix, Map<String, Object> src, Map<String, Object> out) {
        for (Map.Entry<String, Object> e : src.entrySet()) {
            String key = prefix.isEmpty() ? e.getKey() : prefix + "." + e.getKey();
            Object val = e.getValue();
            if (val instanceof Map) {
                flatten(key, (Map<String, Object>) val, out);
            } else {
                out.put(key, val);
            }
        }
    }
}
