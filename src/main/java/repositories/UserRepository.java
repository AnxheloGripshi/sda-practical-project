package repositories;

import entities.User;

public interface UserRepository<T,ID> extends Repository<T,ID>{

    T findByUsernameAndPassword(String username, String password);
}
