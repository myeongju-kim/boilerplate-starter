package org.codegen;

import org.codegen.generator.CodegenExecutor;
import org.codegen.generator.CodegenInputs;
import org.codegen.utils.ArgsParser;

public class CodegenPlugin {

    public static void main(String[] args) throws Exception {

        CodegenInputs in = ArgsParser.parse(args);
        new CodegenExecutor().run(in);
    }
}
