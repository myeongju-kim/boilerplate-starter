package org.codegen.domain;

import org.codegen.utils.Naming;

public class TemplateVars {

    public final String className;
    public final String varName;
    public final String packageName;
    public final String pathName;
    public final String tableName;

    public TemplateVars(String tableName) {
        this.tableName = tableName;
        this.className = Naming.className(tableName);
        this.varName = Naming.varName(tableName);
        this.packageName = Naming.packageName(tableName);
        this.pathName = Naming.pathName(tableName);
    }
}
