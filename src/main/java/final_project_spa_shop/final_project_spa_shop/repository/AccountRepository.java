package final_project_spa_shop.final_project_spa_shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import final_project_spa_shop.final_project_spa_shop.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
	public Optional<AccountEntity> findByUsername(String username);
}
