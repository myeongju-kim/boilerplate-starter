package org.codegen.schema.model;

public class ColumnMeta {

    public final String name;
    public final int sqlType;
    public final String typeName;
    public final int size;
    public final Integer decimalDigits;
    public final boolean nullable;
    public final String defaultValue;
    public final boolean autoIncrement;
    public final String remarks;
    public boolean isPk;

    public ColumnMeta(String name, int sqlType, String typeName, int size, Integer decimalDigits,
        boolean nullable, String defaultValue, boolean autoIncrement, String remarks, boolean isPk) {
        this.name = name;
        this.sqlType = sqlType;
        this.typeName = typeName;
        this.size = size;
        this.decimalDigits = decimalDigits;
        this.nullable = nullable;
        this.defaultValue = defaultValue;
        this.autoIncrement = autoIncrement;
        this.remarks = remarks;
        this.isPk = isPk;
    }

    @Override
    public String toString() {
        return "ColumnMeta(" + name + " " + typeName + "(" + size + (decimalDigits != null ? "," + decimalDigits : "") + ")" +
            (nullable ? "" : " NOT NULL") + (isPk ? " PK" : "") + ")";
    }

}
