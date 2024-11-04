package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.PostRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.PostResponse;
import final_project_spa_shop.final_project_spa_shop.entity.PostEntity;
import final_project_spa_shop.final_project_spa_shop.exception.ErrorCode;
import final_project_spa_shop.final_project_spa_shop.mapper.PostMapper;
import final_project_spa_shop.final_project_spa_shop.repository.CustomerRepository;
import final_project_spa_shop.final_project_spa_shop.repository.PostRepository;
import final_project_spa_shop.final_project_spa_shop.service.IPostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PostService implements IPostService {
	PostRepository postRepository;
	CustomerRepository customerRepository;
	PostMapper mapper;

	@Override
	public List<PostResponse> getAll() {
		return postRepository.findAllByOrderByCreatedAtDesc().stream().map(mapper::toPostResponse).toList();
	}

	@Override
	public PostResponse delete(long id) {
		Optional<PostEntity> result = postRepository.findById(id);
		if (!result.isPresent())
			throw new EntityNotFoundException(ErrorCode.INVALID_POST.name());
		postRepository.deleteById(id);
		return mapper.toPostResponse(result.get());
	}

	@Override
	public PostResponse save(PostRequest request) {
		PostEntity entity = mapper.toPostEntity(request);
		entity.setCustomer(customerRepository.findById(request.getCustomerID())
				.orElseThrow(() -> new EntityNotFoundException(ErrorCode.INVALID_CUSTOMER.name())));
		return mapper.toPostResponse(postRepository.save(entity));
	}

}
