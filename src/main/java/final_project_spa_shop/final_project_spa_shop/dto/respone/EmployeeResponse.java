package final_project_spa_shop.final_project_spa_shop.dto.respone;

import java.sql.Date;
import java.util.Set;

import final_project_spa_shop.final_project_spa_shop.entity.PermissionEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
	long id;
	String username;
	String phoneNumber;
	String email;
	Date birth;
	String fullName;
	Set<PermissionEntity> permissions;
	Date updated_at;
}