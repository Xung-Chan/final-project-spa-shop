package final_project_spa_shop.final_project_spa_shop.dto.respone;

import java.sql.Date;

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
	String password;
	String username;
	String phoneNumber;
	String email;
	Date birth;
	String fullName;
	Date updated_at;
	String image;
}