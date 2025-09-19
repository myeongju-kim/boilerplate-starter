package kr.co.daou.app.category.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import kr.co.daou.app.category.entity.CategoryEntity;

@Mapper
public interface CategoryMapper {

    // TODO: change Object to specific type
    CategoryEntity findById(Object id);

    List<CategoryEntity> findAll();

    int insert(CategoryEntity e);

    int update(CategoryEntity e);

    int deleteBy(CategoryEntity e));
}
