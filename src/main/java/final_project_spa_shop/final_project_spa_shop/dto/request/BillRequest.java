package final_project_spa_shop.final_project_spa_shop.dto.request;

import java.util.Set;

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
public class BillRequest {
	long id;
	@NotNull(message = "NULL_VALUE")
	Set<Long> services;
	boolean status = false;
}