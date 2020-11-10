package repositories.jdbc.utils;

public interface Mapper<T> {

	T map(Object[] obj);
}