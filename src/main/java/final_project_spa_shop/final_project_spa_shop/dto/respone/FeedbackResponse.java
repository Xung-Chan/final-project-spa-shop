package final_project_spa_shop.final_project_spa_shop.dto.respone;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponse {
	long id;
	long customerID;
	String name;
	int rate;
	String description;
	String imagePath;
}