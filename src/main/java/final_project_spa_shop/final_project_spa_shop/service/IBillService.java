package final_project_spa_shop.final_project_spa_shop.service;

import java.util.List;

import final_project_spa_shop.final_project_spa_shop.dto.request.BillRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.BillResponse;

public interface IBillService  {
	
	public List<BillResponse> getAll() ;

	public BillResponse delete(long id) ;
	public BillResponse pay(long id) ;
	public BillResponse create(BillRequest billRequest) ;
//	public BillResponse save(BillRequest object) ;


	// thanh toán
	public BillResponse getById(long id);
}
