package final_project_spa_shop.final_project_spa_shop.service;

import java.util.List;

import final_project_spa_shop.final_project_spa_shop.dto.request.BillRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.BillResponse;

public interface IBillService extends IService<BillResponse, BillRequest> {
	// Lịch sử giao dịch
	public List<BillResponse> getAllByCustomerID(long id);

	// thanh toán
	public BillResponse getById(long id);
}
