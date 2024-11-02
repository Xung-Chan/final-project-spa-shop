package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.FeedbackRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.FeedbackResponse;
import final_project_spa_shop.final_project_spa_shop.entity.CustomerEntity;
import final_project_spa_shop.final_project_spa_shop.entity.FeedbackEntity;
import final_project_spa_shop.final_project_spa_shop.mapper.FeedbackMapper;
import final_project_spa_shop.final_project_spa_shop.repository.CustomerRepository;
import final_project_spa_shop.final_project_spa_shop.repository.FeedbackRepository;
import final_project_spa_shop.final_project_spa_shop.service.IFeedbackSevice;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FeedbackSerice implements IFeedbackSevice {
	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private FeedbackMapper feedbackMapper;

	@Override
	public List<FeedbackResponse> getAll() {
		return feedbackRepository.findAll().stream().map(feedbackMapper::toFeedbackResponse).toList();
	}

	@Override
	public FeedbackResponse delete(long id) {
		Optional<FeedbackEntity> result = feedbackRepository.findById(id);
		if (!result.isPresent())
			throw new EntityNotFoundException("INVALID_FEEDBACK");
		feedbackRepository.deleteById(id);
		return feedbackMapper.toFeedbackResponse(result.get());
	}

	@Override
	public FeedbackResponse save(FeedbackRequest request) {
		FeedbackEntity entity = feedbackMapper.toFeedbackEntity(request);
		long id = entity.getId();
		if (id != 0)
			feedbackRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("INVALID_FEEDBACK"));
		Optional<CustomerEntity> cuOptional = customerRepository.findById(request.getCustomerID());
		if (!cuOptional.isPresent())
			throw new EntityNotFoundException("INVALID_CUSTOMER");
		entity.setCustomer(cuOptional.get());
		return feedbackMapper.toFeedbackResponse(feedbackRepository.save(entity));
	}

}
