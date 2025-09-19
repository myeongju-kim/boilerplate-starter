package kr.co.daou.app.userordermap.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import kr.co.daou.app.userordermap.entity.UserOrderMapEntity;

@Mapper
public interface UserOrderMapMapper {

    // TODO: change Object to specific type
    UserOrderMapEntity findById(Object id);

    List<UserOrderMapEntity> findAll();

    int insert(UserOrderMapEntity e);

    int update(UserOrderMapEntity e);

    int deleteBy(UserOrderMapEntity e));
}
