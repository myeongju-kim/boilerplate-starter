package org.codegen;

import org.codegen.config.ConfigLoader;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.codegen.generator.CodegenExecutor;
import org.codegen.generator.CodegenInputs;
import org.codegen.utils.ArgsParser;

public class CodegenPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getTasks().register("generateCode", GenerateCodeTask.class, task -> {
            task.setGroup("codegen");
            task.setDescription("Generate sources from DB schema");
        });
    }

    // 실제 Task 로직
    public static class GenerateCodeTask extends DefaultTask {

        @TaskAction
        public void run() throws Exception {
            CodegenInputs in = ConfigLoader.load(getProject());
            getLogger().lifecycle("[codegen] Start generation (basePackage={}, url={})",
                in.getBasePackage(), ConfigLoader.maskPwd(in.getJdbcUrl()));
            try {
                new CodegenExecutor().run(in);
            } catch (Exception e) {
                Throwable root = e;
                while (root.getCause() != null) root = root.getCause();
                getLogger().error("[codegen] root-cause: {}", root.toString()); // ★ 여기 찍힘
                throw new RuntimeException("[codegen] Generation failed. url=" +
                    ConfigLoader.maskPwd(in.getJdbcUrl()) + ", user=" + (in.getUsername()==null?"":in.getUsername()) +
                    " (Check DB server, credentials, or JDBC URL)", e);
            }
        }
    }
}
