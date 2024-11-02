package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;
import final_project_spa_shop.final_project_spa_shop.repository.ServiceRepository;
import final_project_spa_shop.final_project_spa_shop.service.IServiceSerice;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiceSerice implements IServiceSerice {
	@Autowired
	private ServiceRepository serviceRepo;

	@Override
	public List<ServiceEntity> getAll() {
		return serviceRepo.findAll();
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
	public ServiceEntity save(ServiceEntity entity) {
		long id = entity.getId();
		if (id != 0)
			serviceRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("INVALID_SERVICE"));
		try {
			return serviceRepo.save(entity);
		} catch (DataIntegrityViolationException e) {
			throw new EntityExistsException("DUPLICATED_SERVICE");
		}
//		return serviceRepo.save(entity);
	}

}
