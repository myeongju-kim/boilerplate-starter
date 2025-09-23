package org.codegen.generator;


public enum GenerationType {
    CONTROLLER("controller.ftl", "controller", "Controller", ".java"),
    SERVICE("service.ftl", "service", "Service", ".java"),
    MAPPER("mapper.ftl", "mapper", "Mapper", ".java"),
    ENTITY("entity.ftl", "entity", "Entity", ".java"),
    MAPPER_XML("mapper-xml.ftl", "", "Mapper", ".xml")
    ;

    private final String templateName;
    private final String layerDir;
    private final String suffix;
    private final String extension;

    GenerationType(String templateName, String layerDir, String suffix, String extension) {
        this.templateName = templateName;
        this.layerDir = layerDir;
        this.suffix = suffix;
        this.extension = extension;
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
    public String extension() {
        return extension;
    }
}
