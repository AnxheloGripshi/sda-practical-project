package config;


import entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateConfig {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();

            configuration.addAnnotatedClass(Dish.class);
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(Reservation.class);
            configuration.addAnnotatedClass(Restaurant.class);
            configuration.addAnnotatedClass(RestaurantTable.class);
            configuration.addAnnotatedClass(Review.class);
            configuration.addAnnotatedClass(Role.class);
            configuration.addAnnotatedClass(Staff.class);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}


