package final_project_spa_shop.final_project_spa_shop.service;

import org.springframework.web.multipart.MultipartFile;

public interface IImageSerive {
	public String saveImage(MultipartFile file,String role) ;
}
