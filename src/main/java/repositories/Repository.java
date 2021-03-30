package repositories;

import java.util.List;

public interface Repository<T, ID> {

    T create(T entity);

    List<T> read();

    T update(T entity);

    T delete(T entity);

    T findById(ID id);


}
