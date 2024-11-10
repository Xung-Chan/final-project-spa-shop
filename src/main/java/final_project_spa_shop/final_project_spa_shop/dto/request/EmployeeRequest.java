package final_project_spa_shop.final_project_spa_shop.dto.request;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
	long id;
	@NotNull(message = "NULL_VALUE")
	@NotEmpty(message = "NULL_VALUE")
	String username;
	@Size(min = 6, message = "INVALID_PASSWORD")
	String password;
	@Pattern(regexp = "^0[1-9][0-9]{8}", message = "INVALID_PHONENUMBER")
	String phoneNumber;
	@Email(message = "INVALID_EMAIL")
	String email;
	@Past(message = "INVALID_TIME")
	Date birth;
	@NotNull(message = "NULL_VALUE")
	String fullName;
	@NotNull(message = "NULL_VALUE")
	long roleID;
	@NotNull(message = "NULL_VALUE")
	MultipartFile image;
	
}