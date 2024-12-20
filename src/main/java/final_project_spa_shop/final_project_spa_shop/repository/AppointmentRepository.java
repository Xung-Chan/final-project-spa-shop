package final_project_spa_shop.final_project_spa_shop.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import final_project_spa_shop.final_project_spa_shop.entity.AppointmentEntity;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
	public List<AppointmentEntity> findByCustomerId(long id);

	public List<AppointmentEntity> findByCustomerAccountUsername(String username);

	public List<AppointmentEntity> findByDate(LocalDate date);

	public List<AppointmentEntity> findByCustomerFullNameIgnoreCaseContaining(String fullName);

	public long countByDate(LocalDate date);

}
