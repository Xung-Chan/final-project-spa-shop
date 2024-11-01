package final_project_spa_shop.final_project_spa_shop.service;

import java.util.List;



public interface IService<response,request> {
	public List<response> getAll();
	public response delete(long id);
	public response save(request object);
//	public T getSingle(long id);
}
