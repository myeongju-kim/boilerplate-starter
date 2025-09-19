package org.codegen;

import java.util.List;
import org.codegen.config.CodegenConfig;
import org.codegen.config.OutputResolver;
import org.codegen.config.TemplateRenderer;
import org.codegen.domain.TemplateVars;
import org.codegen.generator.GenerationType;
import org.codegen.generator.TemplateGenerator;
import org.codegen.schema.SchemaReader;
import org.codegen.schema.config.SchemaConfig;
import org.codegen.schema.model.TableMeta;

public class Main {

    public static void main(String[] args) throws Exception {
        // DB
        SchemaReader reader = new SchemaReader(new SchemaConfig("url", "id", "pwd", null));

        var tables = reader.read();
        CodegenConfig config = new CodegenConfig("kr.co.kingmj.app", "codegen-src");
        OutputResolver outputs = new OutputResolver(config);
        TemplateRenderer renderer = new TemplateRenderer();
        TemplateGenerator gen = new TemplateGenerator(config, outputs, renderer);
        var types = List.of(GenerationType.CONTROLLER, GenerationType.SERVICE, GenerationType.MAPPER,
            GenerationType.ENTITY
        );

        for (TableMeta t : tables) {
            String tableName = t.tableName;
            TemplateVars vars = new TemplateVars(tableName);
            gen.generate(vars, types);

        }
    }
}
