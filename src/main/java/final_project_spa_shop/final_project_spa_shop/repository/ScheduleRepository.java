package final_project_spa_shop.final_project_spa_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import final_project_spa_shop.final_project_spa_shop.entity.ScheduleEntity;
@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long>{

}
