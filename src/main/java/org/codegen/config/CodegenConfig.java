package org.codegen.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CodegenConfig {
    private final String basePackage; // 예: "kr.co.daou.app"
    private final Path projectRoot; // 현재 프로젝트 root
    private final Path outRoot; // 예: Paths.get("generated-src")
    private final Path mapperRoot;

    public CodegenConfig(String basePackage, String outRoot, String mapperRoot) {
        this.basePackage = basePackage;
        this.projectRoot = Paths.get(System.getProperty("user.dir"));
        this.outRoot = projectRoot.resolve(outRoot);
        this.mapperRoot = projectRoot.resolve(mapperRoot);
    }

    public String getBasePackage() { return basePackage; }
    public Path getOutRoot() { return outRoot; }
    public Path getMapperRoot() { return mapperRoot; }

}