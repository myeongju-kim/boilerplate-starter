package kr.co.daou.app.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import kr.co.daou.app.order.entity.OrderEntity;

@Mapper
public interface OrderMapper {

    // TODO: change Object to specific type
    OrderEntity findById(Object id);

    List<OrderEntity> findAll();

    int insert(OrderEntity e);

    int update(OrderEntity e);

    int deleteBy(OrderEntity e));
}
