package final_project_spa_shop.final_project_spa_shop.dto.request;

import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillRequest {
	long id;
	long employeeID;
	long customerID;
	long voucherID;
	Set<Long> services;
	boolean status = false;
}