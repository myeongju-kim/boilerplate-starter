package kr.co.daou.app.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import kr.co.daou.app.account.entity.AccountEntity;

@Mapper
public interface AccountMapper {

    // TODO: change Object to specific type
    AccountEntity findById(Object id);

    List<AccountEntity> findAll();

    int insert(AccountEntity e);

    int update(AccountEntity e);

    int deleteBy(AccountEntity e));
}
