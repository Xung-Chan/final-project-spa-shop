package final_project_spa_shop.final_project_spa_shop.service;

import final_project_spa_shop.final_project_spa_shop.dto.request.AccountRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AccountResponse;

public interface IAccountService {
	public AccountResponse create(AccountRequest accountRequest);

	public AccountResponse update(AccountRequest accountRequest);

	public AccountResponse changePassword(AccountRequest accountRequest);
	public AccountResponse checkPassword(AccountRequest accountRequest);

}
