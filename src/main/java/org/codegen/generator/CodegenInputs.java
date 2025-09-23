package org.codegen.generator;

public class CodegenInputs {

    private String jdbcUrl;
    private String username;
    private String password;
    private String basePackage;
    private String outputRoot;

    public CodegenInputs(String jdbcUrl, String username, String password, String basePackage, String outputRoot) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.basePackage = basePackage;
        this.outputRoot = outputRoot;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public String getOutputRoot() {
        return outputRoot;
    }

    public String getDefaultOutputRoot() {
        return "src/main/java";
    }
}
