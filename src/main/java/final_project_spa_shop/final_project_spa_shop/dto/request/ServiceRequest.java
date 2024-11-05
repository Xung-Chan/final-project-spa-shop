package final_project_spa_shop.final_project_spa_shop.dto.request;


import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {
	long id;
	@NotNull(message = "NULL_VALUE")
	String name;
	@NotNull(message = "NULL_VALUE")
	String description;
	MultipartFile image;
	
}
