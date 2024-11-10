package final_project_spa_shop.final_project_spa_shop.dto.respone;

import java.sql.Date;
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
public class AppointmentResponse {
	long id;
	long customerID;
	String customerName;
	Date date;
	boolean status;
	Set<String> services;
	double discount;
	double cost;
}