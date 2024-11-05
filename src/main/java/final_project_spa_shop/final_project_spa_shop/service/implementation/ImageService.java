package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import final_project_spa_shop.final_project_spa_shop.service.IImageSerive;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageService implements IImageSerive {
	@NonFinal
	@Value("${spa-shop.upload-dir}")
	String pathUpload;

	@Override
	public String saveImage(MultipartFile file, String savedFileName) {
		try {
			String originalFile = file.getOriginalFilename();
			String extension = originalFile.substring(originalFile.lastIndexOf("."));
			Path destination = Paths.get(pathUpload + savedFileName + extension);
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
			}
			return destination.getFileName().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
}
