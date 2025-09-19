package org.codegen.config;

import org.codegen.domain.TemplateVars;

import java.nio.file.Path;
import org.codegen.generator.GenerationType;

/**
 * SRP: 출력 경로 계산 전담.
 */
public class OutputResolver {

    private final CodegenConfig cfg;

    public OutputResolver(CodegenConfig cfg) {
        this.cfg = cfg;
    }

    public Path resolve(TemplateVars vars, String layer, String fileName) {
        return cfg.getOutRoot()
            .resolve(cfg.getBasePackage().replace('.', '/'))
            .resolve(vars.getPackageName())
            .resolve(layer)
            .resolve(fileName);
    }
}