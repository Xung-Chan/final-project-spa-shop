package final_project_spa_shop.final_project_spa_shop.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest {
	long id;
	@NotNull(message = "NULL_VALUE")
	long customerID;
	@NotNull(message = "NULL_VALUE")
	String content;
}