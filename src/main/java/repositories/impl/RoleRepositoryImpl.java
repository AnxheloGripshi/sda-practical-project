package repositories.impl;

import entities.Role;
import org.hibernate.Session;
import repositories.RoleRepository;

public class RoleRepositoryImpl implements RoleRepository<Role,Integer> {


    private final Session session;

    public RoleRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Role findById(Integer id) {

        try {
            return session.find(Role.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
