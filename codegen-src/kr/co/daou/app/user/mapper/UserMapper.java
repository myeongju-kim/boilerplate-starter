package kr.co.daou.app.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import kr.co.daou.app.user.entity.UserEntity;

@Mapper
public interface UserMapper {

    // TODO: change Object to specific type
    UserEntity findById(Object id);

    List<UserEntity> findAll();

    int insert(UserEntity e);

    int update(UserEntity e);

    int deleteBy(UserEntity e));
}
