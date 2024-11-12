package final_project_spa_shop.final_project_spa_shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@Entity
@Table(name ="customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntity extends ProfileEntity {
}	
