package kr.co.daou.app.productcategorymap.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import kr.co.daou.app.productcategorymap.entity.ProductCategoryMapEntity;

@Mapper
public interface ProductCategoryMapMapper {

    // TODO: change Object to specific type
    ProductCategoryMapEntity findById(Object id);

    List<ProductCategoryMapEntity> findAll();

    int insert(ProductCategoryMapEntity e);

    int update(ProductCategoryMapEntity e);

    int deleteBy(ProductCategoryMapEntity e));
}
