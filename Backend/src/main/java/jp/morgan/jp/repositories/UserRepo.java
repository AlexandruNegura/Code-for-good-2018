package jp.morgan.jp.repositories;

import jp.morgan.jp.entities.JpUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<JpUser, Long> {

    JpUser findJpUserByUsername(@Param("username") String username);
}
