package final_project_spa_shop.final_project_spa_shop.dto.request;

import java.sql.Date;

import jakarta.validation.constraints.Future;
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
public class ScheduleRequest {
	long id;
	@NotNull(message = "NULL_VALUE")
	long employeeID;
	@Future(message = "INVALID_TIME")
	Date date;
	boolean status=false;
}
