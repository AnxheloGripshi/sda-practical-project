package repositories;

public interface RestaurantTableRepository<T, ID> {

    T findById(ID id);
}
