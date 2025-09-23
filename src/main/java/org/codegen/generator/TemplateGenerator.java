package org.codegen.generator;

import org.codegen.config.CodegenConfig;
import org.codegen.domain.TemplateVars;
import org.codegen.config.OutputResolver;
import org.codegen.config.TemplateRenderer;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateGenerator {

    private final CodegenConfig cfg;
    private final OutputResolver outputs;
    private final TemplateRenderer renderer;

    public TemplateGenerator(CodegenConfig cfg, OutputResolver outputs, TemplateRenderer renderer) {
        this.cfg = cfg;
        this.outputs = outputs;
        this.renderer = renderer;
    }

    // 모델 선언 다른 경우, 전략 패턴 등 활용해야함
    private Map<String, Object> baseModel(TemplateVars v) {
        Map<String, Object> m = new HashMap<>();
        m.put("package", cfg.getBasePackage());
        m.put("packageName", v.packageName);
        m.put("className", v.className);
        m.put("varName", v.varName);
        m.put("pathName", v.pathName);
        m.put("tableName", v.tableName);
        return m;
    }

    public void generate(TemplateVars v) throws Exception {
        Map<String, Object> model = baseModel(v);
        for (GenerationType t : GenerationType.values()) {
            Path out = outputs.resolve(v, t.layerDir(), v.className + t.suffix() + t.extension(), t == GenerationType.MAPPER_XML);
            renderer.renderToFile(t.templateName(), model, out);
            System.out.println("[write] " + out.toAbsolutePath());
        }
    }
}
