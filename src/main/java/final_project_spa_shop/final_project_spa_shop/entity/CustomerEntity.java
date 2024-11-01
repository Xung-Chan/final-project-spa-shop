package final_project_spa_shop.final_project_spa_shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntity extends AccountEntity {
	@Column(name="bonusPoint")
	long points;
}
