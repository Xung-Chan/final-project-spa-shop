package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.entity.VoucherEntity;
import final_project_spa_shop.final_project_spa_shop.repository.VoucherRepository;
import final_project_spa_shop.final_project_spa_shop.service.IVoucherSerice;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VoucherSerice implements IVoucherSerice {
	@Autowired
	private VoucherRepository voucherRepository;

	@Override
	public List<VoucherEntity> getAll() {
		return voucherRepository.findAll();
	}

	@Override
	public VoucherEntity delete(long id) {
		Optional<VoucherEntity> result = voucherRepository.findById(id);
		if (!result.isPresent())
			throw new EntityNotFoundException("INVALID_VOUCHER");
		voucherRepository.deleteById(id);
		return result.get();
	}

	@Override
	public VoucherEntity save(VoucherEntity entity) {
		long id = entity.getId();
		if (id != 0)
			voucherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("INVALID_VOUCHER"));
		return voucherRepository.save(entity);
	}

}
