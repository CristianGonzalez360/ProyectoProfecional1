package business_logic;

import java.util.List;

public interface Controller<T, ID> {

	void save(T entity);

	void update(T target);

	void deleteById(ID id);

	List<T> readAll();
}