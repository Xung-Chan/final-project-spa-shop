package final_project_spa_shop.final_project_spa_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import final_project_spa_shop.final_project_spa_shop.entity.FeedbackEntity;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity,Long>{

}
