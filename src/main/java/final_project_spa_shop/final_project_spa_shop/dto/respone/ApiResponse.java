package final_project_spa_shop.final_project_spa_shop.dto.respone;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@JsonInclude(value = Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> {
	int code = 1000;
	boolean success = true;
	String message;
	T result;
	public ApiResponse(T result){
		this.code=1000;
		this.success = true;
		this.result=result;
	}
	public ApiResponse(int code, String message){
		this.code = code;
		this.success=false;
		this.message = message;
	}
}
