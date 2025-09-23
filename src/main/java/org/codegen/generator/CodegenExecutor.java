package org.codegen.generator;

import java.util.List;
import org.codegen.config.CodegenConfig;
import org.codegen.config.OutputResolver;
import org.codegen.config.TemplateRenderer;
import org.codegen.domain.TemplateVars;
import org.codegen.schema.SchemaReader;
import org.codegen.schema.config.SchemaConfig;
import org.codegen.schema.model.TableMeta;

// core/CodegenExecutor.java
public class CodegenExecutor {

    public void run(CodegenInputs in) throws Exception {
        //TODO: mysql, pg 등 db종류에 따른 jdbc driver pattern 처리 필요
        SchemaReader reader = new SchemaReader(new SchemaConfig(in.getJdbcUrl(), in.getUsername(), in.getPassword(), null));
        var tables = reader.read();
        String outputRoot = (in.getOutputRoot() == null || in.getOutputRoot().isBlank()) ? in.getDefaultOutputRoot() : in.getOutputRoot();
        CodegenConfig cfg = new CodegenConfig(in.getBasePackage(), outputRoot);
        OutputResolver outputs = new OutputResolver(cfg);
        TemplateRenderer renderer = new TemplateRenderer();
        TemplateGenerator gen = new TemplateGenerator(cfg, outputs, renderer);

        List<GenerationType> types = List.of(GenerationType.CONTROLLER, GenerationType.SERVICE, GenerationType.MAPPER, GenerationType.ENTITY);

        for (TableMeta t : tables) {
            gen.generate(new TemplateVars(t.tableName), types);
        }
    }
}
