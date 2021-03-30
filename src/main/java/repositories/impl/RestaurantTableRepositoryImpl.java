package repositories.impl;

import entities.RestaurantTable;
import org.hibernate.Session;
import repositories.RestaurantTableRepository;

public class RestaurantTableRepositoryImpl implements RestaurantTableRepository<RestaurantTable, Integer> {

    private final Session session;

    public RestaurantTableRepositoryImpl(Session session) {
        this.session = session;
    }


    @Override
    public RestaurantTable findById(Integer tableId) {
        return session.find(RestaurantTable.class, tableId);
    }
}
