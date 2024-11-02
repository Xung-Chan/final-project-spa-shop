package final_project_spa_shop.final_project_spa_shop.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class FeedbackRequest {
	long id;
	@NotNull(message = "NULL_VALUE")
	long customerID;
	@Min(value = 1,message = "INVALID_RATE")
	@Max(value = 5,message = "INVALID_RATE")
	int rate;
	String description;
}