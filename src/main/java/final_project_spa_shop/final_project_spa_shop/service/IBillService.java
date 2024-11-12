package final_project_spa_shop.final_project_spa_shop.service;

import java.util.List;

import final_project_spa_shop.final_project_spa_shop.dto.request.BillRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.BillResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.PaginationResponse;

public interface IBillService {

	public List<BillResponse> getAll();

	public List<BillResponse> getAll(int page);

	public BillResponse delete(long id);

	public BillResponse pay(long id);

	public BillResponse create(BillRequest billRequest);

	public List<BillResponse> getAllThisMonth();

	public List<BillResponse> getAllThisYear();

	public BillResponse getById(long id);

	public PaginationResponse getTotalPage();

}
