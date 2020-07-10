package web.dao;



import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@SuppressWarnings("ALL")
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserById(long id) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE id =:id");
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void deleteUserById(long id) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("DELETE FROM User WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByLogin(String login) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE login =:login");
        query.setParameter("login", login);
        return query.getSingleResult();
    }
}
