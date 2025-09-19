package kr.co.daou.app.address.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import kr.co.daou.app.address.entity.AddressEntity;

@Mapper
public interface AddressMapper {

    // TODO: change Object to specific type
    AddressEntity findById(Object id);

    List<AddressEntity> findAll();

    int insert(AddressEntity e);

    int update(AddressEntity e);

    int deleteBy(AddressEntity e));
}
