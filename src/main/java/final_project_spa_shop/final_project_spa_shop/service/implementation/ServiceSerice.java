package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.ServiceRequest;
import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;
import final_project_spa_shop.final_project_spa_shop.exception.ErrorCode;
import final_project_spa_shop.final_project_spa_shop.mapper.ServiceMapper;
import final_project_spa_shop.final_project_spa_shop.repository.ServiceRepository;
import final_project_spa_shop.final_project_spa_shop.service.IImageSerive;
import final_project_spa_shop.final_project_spa_shop.service.IServiceSerice;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ServiceSerice implements IServiceSerice {
	ServiceRepository serviceRepo;
	IImageSerive imageSerive;
	ServiceMapper serviceMapper;

	@Override
	public List<ServiceEntity> getAll() {
		return serviceRepo.findAll();
	}

	@Override
	public List<ServiceEntity> getAllLimit(int limit) {
		Pageable pages = PageRequest.of(0, limit);
		return serviceRepo.findAll(pages).getContent();
	}

	@Override
	public ServiceEntity delete(long id) {
		Optional<ServiceEntity> result = serviceRepo.findById(id);
		if (!result.isPresent())
			throw new EntityNotFoundException("INVALID_SERVICE");
		serviceRepo.deleteById(id);
		return result.get();
	}

	@Override
	public ServiceEntity save(ServiceRequest request) {
		long id = 0;
		try {
			ServiceEntity serviceEntity = serviceMapper.toServiceEntity(request);
			id = serviceEntity.getId();
			if (id == 0) {
				serviceEntity = serviceRepo.save(serviceEntity);
				id=serviceEntity.getId();
			}
			else
				serviceRepo.findById(id).orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_SERVICE.name()));
			String imagePath = imageSerive.saveImage(request.getImage(),
					"/customer/img/services-" + serviceEntity.getId());
			serviceEntity.setImagePath(imagePath);
			return serviceRepo.save(serviceEntity);
		} catch (DataIntegrityViolationException e) {
			throw new EntityExistsException("DUPLICATED_SERVICE");
		} catch (RuntimeException e) {
			serviceRepo.deleteById(id);
			throw e;
		}
	}

}
