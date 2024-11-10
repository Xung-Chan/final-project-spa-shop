package final_project_spa_shop.final_project_spa_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import final_project_spa_shop.final_project_spa_shop.dto.request.AccountRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AccountResponse;
import final_project_spa_shop.final_project_spa_shop.entity.AccountEntity;


@Mapper(componentModel = "spring")
public interface AccountMapper {
	@Mapping(target = "roleID",source = "role.id")
	@Mapping(target = "profileID",source = "profile.id")
	AccountResponse toAccountResponse(AccountEntity entity);
	@Mapping (target = "role",ignore = true)
	@Mapping (target = "profile",ignore = true)
	AccountEntity toAccountEntity(AccountRequest request);
}
