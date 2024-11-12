package final_project_spa_shop.final_project_spa_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import final_project_spa_shop.final_project_spa_shop.entity.ScheduleEntity;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
	List<ScheduleEntity> findByDate(LocalDate date);
	List<ScheduleEntity> findByEmployeeAccountUsername(String username);
}
