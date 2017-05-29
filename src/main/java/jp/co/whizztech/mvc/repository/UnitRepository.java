package jp.co.whizztech.mvc.repository;

import jp.co.whizztech.mvc.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hilo on 2017/05/24.
 */
@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    Unit findById(Long id);
}
