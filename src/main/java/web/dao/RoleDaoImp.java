package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.TypedQuery;

@Repository
public class RoleDaoImp implements RoleDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public Role getAdmin() {
        TypedQuery<Role> query = sessionFactory.getCurrentSession().createQuery("FROM Role WHERE name = 'ROLE_ADMIN'");
        return query.getSingleResult();
    }

    @Override
    public Role getUser() {
        TypedQuery<Role> query = sessionFactory.getCurrentSession().createQuery("FROM Role WHERE name = 'ROLE_USER'");
        return query.getSingleResult();
    }
}
