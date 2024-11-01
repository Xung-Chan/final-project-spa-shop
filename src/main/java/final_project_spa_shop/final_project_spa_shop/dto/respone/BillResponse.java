package final_project_spa_shop.final_project_spa_shop.dto.respone;

import java.util.Set;

import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillResponse {
	long id;
	String employeeName;
	String customerName;
	double discount;
	Set<ServiceEntity> services;
	boolean status = false;
}