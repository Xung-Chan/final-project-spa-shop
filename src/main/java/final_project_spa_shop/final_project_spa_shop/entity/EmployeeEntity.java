package final_project_spa_shop.final_project_spa_shop.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name ="employee")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeEntity extends AccountEntity{
	@ManyToMany
	@JoinTable(name ="employee_permission",joinColumns = @JoinColumn(name="employee_id"),
	inverseJoinColumns = @JoinColumn(name="permission_id"))
	Set<PermissionEntity> permissions;
}
