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

    public Path resolve(TemplateVars vars, String layer, String fileName, boolean isXml) {
        if (isXml) {
            return cfg.getMapperRoot()
                .resolve(vars.packageName)
                .resolve(fileName);
        }
        return cfg.getOutRoot()
            .resolve(cfg.getBasePackage().replace('.', '/'))
            .resolve(vars.packageName)
            .resolve(layer)
            .resolve(fileName);
    }
}