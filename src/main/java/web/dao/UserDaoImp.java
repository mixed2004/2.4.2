package web.dao;


import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@SuppressWarnings("ALL")
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager
                .persist(user);
    }

    @Override
    public void update(User user) {
        entityManager
                .merge(user);
    }

    @Override
    public List<User> listUsers() {
        return entityManager
                .createQuery("from User")
                .getResultList();
    }

    @Override
    public void deleteUser(User user) {
        entityManager
                .remove(user);
    }

    @Override
    public User getUserById(long id) {
        return (User) entityManager
                .createQuery("FROM User WHERE id =:id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getUserByLogin(String login) {
        return (User) entityManager
                .createQuery("FROM User WHERE login =:login")
                .setParameter("login", login)
                .getSingleResult();
    }
}
