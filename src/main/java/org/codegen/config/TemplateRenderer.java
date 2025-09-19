package org.codegen.config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;


/**
 * FreeMarker 설정/렌더링 전담.
 */
public class TemplateRenderer {

    private final Configuration fm;

    public TemplateRenderer() {
        fm = new Configuration(Configuration.VERSION_2_3_32);
        fm.setDefaultEncoding("UTF-8");
        fm.setClassLoaderForTemplateLoading(Thread.currentThread().getContextClassLoader(), "/templates");
        fm.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }


    public void renderToFile(String templateName, Map<String, Object> model, Path outFile) throws Exception {
        Files.createDirectories(outFile.getParent());
        Template tpl = fm.getTemplate(templateName);
        try (Writer w = new OutputStreamWriter(Files.newOutputStream(outFile), StandardCharsets.UTF_8)) {
            tpl.process(model, w);
        }
    }
}