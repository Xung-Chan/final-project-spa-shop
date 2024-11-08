package final_project_spa_shop.final_project_spa_shop.dto.respone;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
	long id;
	long customerID;
	String customerName;
	String content;
	Date createdAt;
	String imagePath;
	int likeNumber;
}
