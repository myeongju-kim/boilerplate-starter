package kr.co.daou.app.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import kr.co.daou.app.payment.entity.PaymentEntity;

@Mapper
public interface PaymentMapper {

    // TODO: change Object to specific type
    PaymentEntity findById(Object id);

    List<PaymentEntity> findAll();

    int insert(PaymentEntity e);

    int update(PaymentEntity e);

    int deleteBy(PaymentEntity e));
}
