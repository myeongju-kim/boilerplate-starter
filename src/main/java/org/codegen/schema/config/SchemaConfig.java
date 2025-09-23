package org.codegen.schema.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class SchemaConfig {

    public final String url;
    public final String username;
    public final String password;
    /**
     * Postgres 등은 "public" 등 스키마명, MySQL은 보통 null (catalog에 DB명)
     */
    public final String schemaPattern;
    public final String driverClassName;

    /**
     * 기본: {"TABLE"}; VIEW까지 원하면 {"TABLE","VIEW"}
     */
    public final String[] tableTypes;

    /**
     * include가 비어있지 않으면 include된 테이블만 처리
     */
    public final Set<String> includeTables;
    /**
     * exclude는 무시할 테이블
     */
    public final Set<String> excludeTables;

    public SchemaConfig(String url, String username, String password, String schemaPattern, String driverClassName) {
        this(url, username, password, schemaPattern, driverClassName,
            new String[]{"TABLE"},
            Collections.emptySet(),
            Collections.emptySet());
    }

    public SchemaConfig(String url, String username, String password, String schemaPattern, String driverClassName,
        String[] tableTypes, Set<String> includeTables, Set<String> excludeTables) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.schemaPattern = schemaPattern;
        this.driverClassName = driverClassName;
        this.tableTypes = tableTypes == null ? new String[]{"TABLE"} : tableTypes;
        this.includeTables = includeTables == null ? Collections.emptySet() : includeTables;
        this.excludeTables = excludeTables == null ? Collections.emptySet() : excludeTables;
    }

    @Override
    public String toString() {
        return "Config{url='" + url + "', schemaPattern='" + schemaPattern + "', types=" + Arrays.toString(tableTypes) + "}";
    }
}