package final_project_spa_shop.final_project_spa_shop.service.implementation;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.AccountRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AccountResponse;
import final_project_spa_shop.final_project_spa_shop.entity.AccountEntity;
import final_project_spa_shop.final_project_spa_shop.exception.ErrorCode;
import final_project_spa_shop.final_project_spa_shop.mapper.AccountMapper;
import final_project_spa_shop.final_project_spa_shop.repository.AccountRepository;
import final_project_spa_shop.final_project_spa_shop.repository.ProfileReposiotory;
import final_project_spa_shop.final_project_spa_shop.repository.RoleRepository;
import final_project_spa_shop.final_project_spa_shop.service.IAccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AccountService implements IAccountService {
	AccountRepository accountRepository;
	AccountMapper accountMapper;
	RoleRepository roleRepository;
	ProfileReposiotory profileReposiotory;
	PasswordEncoder encoder;

	@Override
	public AccountResponse create(AccountRequest accountRequest) {
		AccountEntity accountEntity = accountMapper.toAccountEntity(accountRequest);
		accountEntity.setRole(roleRepository.findById(accountRequest.getRoleID())
				.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_ROLE.getMessage())));
		try {
			return accountMapper.toAccountResponse(accountRepository.save(accountEntity));
		} catch (DataIntegrityViolationException ex) {
			throw new RuntimeException(ErrorCode.DUPLICATED_USERNAME.getMessage());
		}
	}

	@Override
	public AccountResponse update(AccountRequest accountRequest) {
		String username = accountRequest.getUsername();
		accountRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_USERNAME.getMessage()));
		AccountEntity accountEntity = accountMapper.toAccountEntity(accountRequest);
		accountEntity.setRole(roleRepository.findById(accountRequest.getRoleID())
				.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_ROLE.getMessage())));
		accountEntity.setProfile(profileReposiotory.findById(accountRequest.getProfileID()).orElseThrow(()->new RuntimeException(ErrorCode.INVALID_PROFILE.getMessage())));
		try {
			return accountMapper.toAccountResponse(accountRepository.save(accountEntity));
		} catch (DataIntegrityViolationException ex) {
			throw new RuntimeException(ErrorCode.DUPLICATED_USERNAME.getMessage());
		}
	}
	@Override
	public AccountResponse changePassword(AccountRequest accountRequest) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);
		AccountEntity oldAccountEntity = accountRepository.findByUsername(username).orElseThrow(()->new RuntimeException(ErrorCode.INVALID_USERNAME.getMessage()));
		oldAccountEntity.setPassword(encoder.encode(accountRequest.getPassword()));
		return accountMapper.toAccountResponse(accountRepository.save(oldAccountEntity));
	}
	@Override
	public AccountResponse checkPassword(AccountRequest accountRequest) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);
		AccountEntity oldAccountEntity = accountRepository.findByUsername(username).orElseThrow(()->new RuntimeException(ErrorCode.INVALID_USERNAME.getMessage()));
		if(!encoder.matches(accountRequest.getPassword(), oldAccountEntity.getPassword()))
			throw new RuntimeException(ErrorCode.WRONG_PASSWORD.name());
		return accountMapper.toAccountResponse(oldAccountEntity);
	}

}
