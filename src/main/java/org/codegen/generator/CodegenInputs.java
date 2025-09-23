package org.codegen.generator;

public class CodegenInputs {

    private String jdbcUrl;
    private String username;
    private String password;
    private String basePackage;
    private String outputRoot;
    private String mapperRoot;
    private String driverClassName;
    private boolean autoGenerate;

    public CodegenInputs(String jdbcUrl, String username, String password, String basePackage, String outputRoot, String mapperRoot, String driverClassName, boolean autoGenerate) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.basePackage = basePackage;
        this.outputRoot = outputRoot;
        this.mapperRoot = mapperRoot;
        this.driverClassName = driverClassName;
        this.autoGenerate = autoGenerate;
    }
    public boolean isAutoGenerate() {
        return autoGenerate;
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

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getOutputRoot() {
        return outputRoot;
    }

    public String getDefaultOutputRoot() {
        return "src/main/java";
    }

    public String getMapperRoot() {
        return mapperRoot;
    }

    public String getDefaultMapperRoot() {
        return "src/main/resources/mappers";
    }
}
