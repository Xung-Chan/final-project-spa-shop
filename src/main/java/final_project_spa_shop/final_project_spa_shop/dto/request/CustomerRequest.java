package final_project_spa_shop.final_project_spa_shop.dto.request;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
	long id;
	@Pattern(regexp = "^0[1-9][0-9]{8}", message = "INVALID_PHONENUMBER")
	String phoneNumber;
	@Email(message = "INVALID_EMAIL")
	String email;
	@Past(message = "INVALID_TIME")
	Date birth;
	@NotNull(message = "NULL_VALUE")
	String fullName;
	MultipartFile image;
	long points = 0;
}