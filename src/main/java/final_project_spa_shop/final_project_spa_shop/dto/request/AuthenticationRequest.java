package final_project_spa_shop.final_project_spa_shop.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
	@NotNull(message = "NULL_VALUE")
	String username;
	@NotNull(message = "NULL_VALUE")
	@Size(min = 6,message = "INVALID_PASSWORD")
	String password;
}
