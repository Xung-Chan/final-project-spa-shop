package final_project_spa_shop.final_project_spa_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import final_project_spa_shop.final_project_spa_shop.entity.BillEntity;

@Repository
public interface BillRepository extends JpaRepository<BillEntity,Long>{
	public List<BillEntity> findByCustomerId(long id);
}
