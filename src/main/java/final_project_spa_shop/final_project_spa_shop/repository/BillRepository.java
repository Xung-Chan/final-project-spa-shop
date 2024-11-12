package final_project_spa_shop.final_project_spa_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import final_project_spa_shop.final_project_spa_shop.entity.BillEntity;


@Repository
public interface BillRepository extends JpaRepository<BillEntity,Long>{
	@Query("FROM BillEntity b WHERE b.status = true AND year(b.updated_at) = :year")
	List<BillEntity> findPaidBillsByYear(@Param("year") int year);
	@Query("FROM BillEntity b WHERE b.status = true AND year(b.updated_at) = :year and month(b.updated_at)=:month")
	List<BillEntity> findPaidBillsByYearAndMonth(@Param("year") int year, @Param("month") int month);

}
