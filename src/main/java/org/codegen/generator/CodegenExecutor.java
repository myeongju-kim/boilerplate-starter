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
        SchemaReader reader = new SchemaReader(new SchemaConfig(in.getJdbcUrl(), in.getUsername(), in.getPassword(), null, in.getDriverClassName()));
        var tables = reader.read();
        String outputRoot = (in.getOutputRoot() == null || in.getOutputRoot().isBlank()) ? in.getDefaultOutputRoot() : in.getOutputRoot();
        String mapperRoot = (in.getMapperRoot() == null || in.getMapperRoot().isBlank()) ? in.getDefaultMapperRoot() : in.getMapperRoot();
        CodegenConfig cfg = new CodegenConfig(in.getBasePackage(), outputRoot, mapperRoot);
        OutputResolver outputs = new OutputResolver(cfg);
        TemplateRenderer renderer = new TemplateRenderer();
        TemplateGenerator gen = new TemplateGenerator(cfg, outputs, renderer);

        for (TableMeta t : tables) {
            gen.generate(new TemplateVars(t.tableName));
        }
    }
}
