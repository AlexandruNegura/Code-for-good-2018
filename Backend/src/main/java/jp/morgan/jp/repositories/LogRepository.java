package jp.morgan.jp.repositories;

import jp.morgan.jp.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
