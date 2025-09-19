package kr.co.daou.app.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import kr.co.daou.app.product.entity.ProductEntity;

@Mapper
public interface ProductMapper {

    // TODO: change Object to specific type
    ProductEntity findById(Object id);

    List<ProductEntity> findAll();

    int insert(ProductEntity e);

    int update(ProductEntity e);

    int deleteBy(ProductEntity e));
}
