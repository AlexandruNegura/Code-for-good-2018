package jp.morgan.jp.repositories;

import jp.morgan.jp.entities.Carrer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<Carrer, Long> {

    Carrer findJpUserByUsername(@Param("username") String username);
}
