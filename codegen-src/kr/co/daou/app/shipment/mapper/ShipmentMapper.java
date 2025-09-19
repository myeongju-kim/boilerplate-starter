package kr.co.daou.app.shipment.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import kr.co.daou.app.shipment.entity.ShipmentEntity;

@Mapper
public interface ShipmentMapper {

    // TODO: change Object to specific type
    ShipmentEntity findById(Object id);

    List<ShipmentEntity> findAll();

    int insert(ShipmentEntity e);

    int update(ShipmentEntity e);

    int deleteBy(ShipmentEntity e));
}
