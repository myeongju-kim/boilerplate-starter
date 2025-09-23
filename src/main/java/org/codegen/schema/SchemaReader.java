// file: src/main/java/org/codegen/schema/SchemaReader.java
package org.codegen.schema;

import java.sql.*;
import java.util.*;
import org.codegen.schema.config.SchemaConfig;
import org.codegen.schema.model.ColumnMeta;
import org.codegen.schema.model.TableMeta;

public final class SchemaReader {

    private final SchemaConfig config;

    public SchemaReader(SchemaConfig config) {
        this.config = Objects.requireNonNull(config, "config");
    }

    /**
     * 스키마 전체 스캔 (기본: TABLE 만)
     */
    public List<TableMeta> read() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(config.url, config.username, config.password)) {
            DatabaseMetaData meta = conn.getMetaData();
            String catalog = conn.getCatalog();
            String schemaPattern = config.schemaPattern;

            List<TableMeta> out = new ArrayList<>();
            try (ResultSet rs = meta.getTables(catalog, schemaPattern, "%", config.tableTypes)) {
                while (rs.next()) {
                    String tableName = rs.getString("TABLE_NAME");
                    String tableSchema = rs.getString("TABLE_SCHEM"); // 일부 DB는 null 일 수 있음

                    if (!config.includeTables.isEmpty() && !config.includeTables.contains(tableName)) {
                        continue;
                    }
                    if (config.excludeTables.contains(tableName)) {
                        continue;
                    }

                    // PK 수집
                    Set<String> pks = new LinkedHashSet<>();
                    try (ResultSet pk = meta.getPrimaryKeys(catalog, schemaPattern, tableName)) {
                        while (pk.next()) {
                            pks.add(pk.getString("COLUMN_NAME"));
                        }
                    }

                    // 컬럼 수집
                    List<ColumnMeta> cols = new ArrayList<>();
                    try (ResultSet c = meta.getColumns(catalog, schemaPattern, tableName, "%")) {
                        while (c.next()) {
                            ColumnMeta col = new ColumnMeta(
                                c.getString("COLUMN_NAME"),
                                c.getInt("DATA_TYPE"),            // java.sql.Types
                                c.getString("TYPE_NAME"),         // 벤더 타입명
                                c.getInt("COLUMN_SIZE"),
                                getIntOrNull(c, "DECIMAL_DIGITS"),
                                c.getInt("NULLABLE") == DatabaseMetaData.columnNullable,
                                trimToNull(safeGet(c, "COLUMN_DEF")),
                                "YES".equalsIgnoreCase(safeGet(c, "IS_AUTOINCREMENT")),
                                trimToEmpty(safeGet(c, "REMARKS")),
                                false // isPk -> 아래에서 마크
                            );
                            cols.add(col);
                        }
                    }
                    // PK 마킹
                    for (ColumnMeta col : cols) {
                        if (pks.contains(col.name)) {
                            col.isPk = true;
                        }
                    }

                    out.add(new TableMeta(tableSchema, tableName, Collections.unmodifiableList(cols)));
                }
            }
            return Collections.unmodifiableList(out);
        } catch (SQLException e) {
            throw new SQLException("Failed to read schema from " + config.url, e);
        }
    }

    private static String safeGet(ResultSet rs, String col) {
        try {
            return rs.getString(col);
        } catch (SQLException ignore) {
            return null;
        }
    }

    private static Integer getIntOrNull(ResultSet rs, String col) throws SQLException {
        int v = rs.getInt(col);
        return rs.wasNull() ? null : v;
    }

    private static String trimToNull(String s) {
        return s == null ? null : (s.isBlank() ? null : s.trim());
    }

    private static String trimToEmpty(String s) {
        return s == null ? "" : s.trim();
    }
}
