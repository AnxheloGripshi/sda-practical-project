package repositories;

public interface RoleRepository<T, ID> {

    T findById(ID id);
}
