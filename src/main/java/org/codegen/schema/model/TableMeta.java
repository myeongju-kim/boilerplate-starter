package org.codegen.schema.model;

import java.util.List;

public class TableMeta {

    public final String schema;
    public final String tableName;
    public final List<ColumnMeta> columns;

    public TableMeta(String schema, String tableName, List<ColumnMeta> columns) {
        this.schema = schema;
        this.tableName = tableName;
        this.columns = columns;
    }

    public ColumnMeta primaryKeyOrNull() {
        for (ColumnMeta c : columns) {
            if (c.isPk) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "TableMeta(" + (schema == null ? "" : schema + ".") + tableName + ", cols=" + columns.size() + ")";
    }

}
