package org.codegen.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CodegenConfig {
    private final String basePackage; // 예: "kr.co.daou.app"
    private final Path outRoot; // 예: Paths.get("generated-src")

    public CodegenConfig(String basePackage, String outRoot) {
        this.basePackage = basePackage;
        this.outRoot = Paths.get(outRoot);
    }

    public String getBasePackage() { return basePackage; }
    public Path getOutRoot() { return outRoot; }

}