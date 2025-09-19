package org.codegen.generator;


public enum GenerationType {
    CONTROLLER("controller.ftl", "controller", "Controller"),
    SERVICE("service.ftl", "service", "Service"),
    MAPPER("mapper.ftl", "mapper", "Mapper"),
    ENTITY("entity.ftl", "entity", "Entity"),
    ;

    private final String templateName;
    private final String layerDir;
    private final String suffix;

    GenerationType(String templateName, String layerDir, String suffix) {
        this.templateName = templateName;
        this.layerDir = layerDir;
        this.suffix = suffix;
    }

    public String templateName() {
        return templateName;
    }

    public String layerDir() {
        return layerDir;
    }

    public String suffix() {
        return suffix;
    }
}
