package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.ServiceRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.PaginationResponse;
import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;
import final_project_spa_shop.final_project_spa_shop.exception.ErrorCode;
import final_project_spa_shop.final_project_spa_shop.mapper.ServiceMapper;
import final_project_spa_shop.final_project_spa_shop.repository.ServiceRepository;
import final_project_spa_shop.final_project_spa_shop.service.IImageSerive;
import final_project_spa_shop.final_project_spa_shop.service.IServiceSerice;
import jakarta.persistence.EntityExistsException;
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
	public ServiceEntity getById(long id) {
		return serviceRepo.findById(id).orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_SERVICE.name()));
	}

	@Override
	public List<ServiceEntity> getAll() {
		return serviceRepo.findAll();
	}

	@Override
	public List<ServiceEntity> getAll(int page) {
		Pageable pageable = PageRequest.of(page - 1, 5);
		return serviceRepo.findAll(pageable).getContent();
	}

	@Override
	public PaginationResponse getTotalPage() {
		return new PaginationResponse((int) Math.ceil(1.0 * serviceRepo.count() / 5));
	}

	@Override
	public List<ServiceEntity> getAllLimit(int limit) {
		Pageable pages = PageRequest.of(0, limit);
		return serviceRepo.findAll(pages).getContent();
	}

	@Override
	public ServiceEntity save(ServiceRequest request) {
		long id = 0;
		try {
			ServiceEntity serviceEntity = serviceMapper.toServiceEntity(request);
			serviceEntity = serviceRepo.save(serviceEntity);
			id = serviceEntity.getId();
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

	@Override
	public ServiceEntity update(ServiceRequest request) {
		try {
			ServiceEntity oldServiceEntity = serviceRepo.findById(request.getId())
					.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_SERVICE.name()));
			ServiceEntity serviceEntity = serviceMapper.toServiceEntity(request);
			System.out.println(request.isStatus());
			if (request.getImage() != null) {
				String imagePath = imageSerive.saveImage(request.getImage(),
						"/customer/img/services-" + serviceEntity.getId());
				serviceEntity.setImagePath(imagePath);
			} else
				serviceEntity.setImagePath(oldServiceEntity.getImagePath());
			return serviceRepo.save(serviceEntity);
		} catch (DataIntegrityViolationException e) {
			throw new EntityExistsException("DUPLICATED_SERVICE");
		}
	}


}
