package final_project_spa_shop.final_project_spa_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import final_project_spa_shop.final_project_spa_shop.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
	public List<PostEntity> findAllByOrderByCreatedAtDesc();
}
