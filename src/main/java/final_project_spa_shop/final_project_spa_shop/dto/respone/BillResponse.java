package final_project_spa_shop.final_project_spa_shop.dto.respone;

import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillResponse {
	long id;
	Set<String> services;
	boolean status = false;
	double cost;
}