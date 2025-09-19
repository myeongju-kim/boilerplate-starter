package org.codegen.domain;

import org.codegen.utils.Naming;

public class TemplateVars {

    public final String className;
    public final String varName;
    public final String packageName;
    public final String pathName;

    public TemplateVars(String tableName) {
        this.className = Naming.className(tableName);
        this.varName = Naming.varName(tableName);
        this.packageName = Naming.packageName(tableName);
        this.pathName = Naming.pathName(tableName);
    }

    public String getClassName() {
        return className;
    }

    public String getVarName() {
        return varName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPathName() {
        return pathName;
    }
}
