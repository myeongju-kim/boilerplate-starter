package ${package}.${packageName}.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import ${package}.${packageName}.entity.${className}Entity;

@Mapper
public interface ${className}Mapper {

    // TODO: change Object to specific type
    ${className}Entity findById(Object id);

    List<${className}Entity> findAll();

    int insert(${className}Entity e);

    int update(${className}Entity e);

    int deleteBy(${className}Entity e));
}
