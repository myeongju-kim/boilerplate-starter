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
        m.put("packageName", v.getPackageName());
        m.put("className", v.getClassName());
        m.put("varName", v.getVarName());
        m.put("pathName", v.getPathName());  // 모두 공통으로 사용 (컨트롤러만 필요해도 그냥 포함)
        return m;
    }

    public void generate(TemplateVars v, List<GenerationType> types) throws Exception {
        Map<String, Object> model = baseModel(v);
        for (GenerationType t : types) {
            Path out = outputs.resolve(v, t.layerDir(), v.getClassName() + t.suffix() + ".java");
            renderer.renderToFile(t.templateName(), model, out);
            System.out.println("[write] " + out.toAbsolutePath());
        }
    }
}
