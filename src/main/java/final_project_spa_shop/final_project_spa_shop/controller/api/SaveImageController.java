package final_project_spa_shop.final_project_spa_shop.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import final_project_spa_shop.final_project_spa_shop.service.IImageSerive;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class SaveImageController {
	IImageSerive imageSerive;
	@PostMapping("/file")
	public String saveFile(@RequestParam MultipartFile file) {
		return imageSerive.saveImage(file,"/customer/cc");
	}
}
